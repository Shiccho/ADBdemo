package com.scalar.db.storage.cosmos;

import java.util.Optional;

public class CosmosAdminTableMetadataDatabaseConfigTest extends CosmosAdminTestBase {
  @Override
  Optional<String> getTableMetadataDatabaseConfig() {
    return Optional.of("my_meta_ns");
  }
}
