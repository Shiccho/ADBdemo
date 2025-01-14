package com.scalar.db.storage.cosmos;

import static com.scalar.db.util.ScalarDbUtils.getFullTableName;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.CosmosStoredProcedure;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.google.common.collect.ImmutableList;
import com.scalar.db.config.DatabaseConfig;
import com.scalar.db.util.AdminTestUtils;
import java.util.Properties;

public class CosmosAdminTestUtils extends AdminTestUtils {

  private final CosmosClient client;
  private final String metadataDatabase;

  public CosmosAdminTestUtils(Properties properties) {
    super(properties);
    CosmosConfig config = new CosmosConfig(new DatabaseConfig(properties));
    client =
        new CosmosClientBuilder()
            .endpoint(config.getEndpoint())
            .key(config.getKey())
            .directMode()
            .consistencyLevel(ConsistencyLevel.STRONG)
            .buildClient();
    metadataDatabase =
        new CosmosConfig(new DatabaseConfig(properties))
            .getTableMetadataDatabase()
            .orElse(CosmosAdmin.METADATA_DATABASE);
  }

  @Override
  public void dropMetadataTable() {
    client.getDatabase(metadataDatabase).delete();
    try {
      client.getDatabase(metadataDatabase).read();
    } catch (CosmosException e) {
      if (e.getStatusCode() != 404) {
        throw new RuntimeException("Dropping the metadata table failed", e);
      }
    }
  }

  @Override
  public void truncateMetadataTable() {
    CosmosContainer container =
        client.getDatabase(metadataDatabase).getContainer(CosmosAdmin.METADATA_CONTAINER);

    CosmosPagedIterable<Record> records =
        container.queryItems("SELECT t.id FROM t", new CosmosQueryRequestOptions(), Record.class);
    records.forEach(
        record ->
            container.deleteItem(
                record.getId(), new PartitionKey(record.getId()), new CosmosItemRequestOptions()));
  }

  @Override
  public void corruptMetadata(String namespace, String table) {
    CosmosTableMetadata corruptedMetadata = new CosmosTableMetadata();
    corruptedMetadata.setId(getFullTableName(namespace, table));
    corruptedMetadata.setPartitionKeyNames(ImmutableList.of("corrupted"));

    CosmosContainer container =
        client.getDatabase(metadataDatabase).getContainer(CosmosAdmin.METADATA_CONTAINER);
    container.upsertItem(corruptedMetadata);
  }

  /**
   * Retrieve the stored procedure for the given table
   *
   * @param namespace a namespace
   * @param table a table
   * @return the stored procedure
   */
  public CosmosStoredProcedure getTableStoredProcedure(String namespace, String table) {
    return client
        .getDatabase(namespace)
        .getContainer(table)
        .getScripts()
        .getStoredProcedure(CosmosAdmin.STORED_PROCEDURE_FILE_NAME);
  }
}
