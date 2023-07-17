package sample;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.util.FileUtil;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MaaSWebUI {
  private static final Javalin app = Javalin.create(config -> {
  config.staticFiles.add(staticFiles -> {
    staticFiles.directory = "/public"; // the directory where your files are located
    staticFiles.location = Location.CLASSPATH;      // Location.CLASSPATH (jar) or Location.EXTERNAL (file system)
    staticFiles.precompress = false;                // if the files should be pre-compressed and cached in memory (optimization)
    staticFiles.aliasCheck = null;                  // you can configure this to enable symlinks (= ContextHandler.ApproveAliases())
    staticFiles.skipFileFunction = req -> false;    // you can add custom mimetypes for extensions
  });
}).start(7070);

  private static final Map<String, String> reservations = new HashMap<>();
  private static UserSystem usersystem;
  private static PathFindSystem pathFindSystem;
  private static final Html htmlIndex = new Html("index.html");;
  private static final Html htmlSignUp = new Html("signup.html");
  private static final Html htmlLogin = new Html("login.html");
  private static final Html htmlRoute = new Html("route.html");
  private static final Html htmlRouteSearch = new Html("routeSearch.html");
  private static String date = "";
  private static String depTime = "";
  private static ArrayList<ArrayList<MyPath>> roots; 
  public MaaSWebUI(String scalarDBProperties) throws Exception{
    this.usersystem = new UserSystem(scalarDBProperties);
    this.pathFindSystem = new PathFindSystem(scalarDBProperties);
    System.out.println(Paths.get("").toAbsolutePath().toString());
    reservations.put("saturday", "No reservation");
    reservations.put("sunday", "No reservation");
    
    app.post("/signup", ctx -> {
      boolean isCorrect = usersystem.signup(ctx.formParam("id"), ctx.formParam("password"));
      if (isCorrect) {
        ctx.html(htmlLogin.getHtml() + "You Successfully Signed Up.");
      } else {
        ctx.html(htmlSignUp.getHtml() + "Failed, This userid is already in use.");
      }
    });

    app.post("/login", ctx -> {
        boolean isCorrect = usersystem.login(ctx.formParam("id"), ctx.formParam("password"));
        if (isCorrect) {
          ctx.html(htmlRouteSearch.getHtml() + "You successfully Logged in");
        } else {
          ctx.html(htmlLogin.getHtml() + "Failed, Please Review Login ID and Passward");
        }
    });
    
    

    app.post("/search-path", ctx -> {
      roots = pathFindSystem.getRoots(ctx.formParam("src"), ctx.formParam("dst"));
      date = ctx.formParam("date");
      depTime = ctx.formParam("time");
      for (ArrayList<MyPath> root : roots) {
        System.out.println(root.size());
        for (MyPath edge : root) {
          String src = edge.getSrc();
          String dst = edge.getDst();
          int time = edge.getTime();
          int fee = edge.getFee();
          int trans = edge.getTrafficroot();
          String means = "";
          if (trans == 0) means = "Train";
          if (trans == 1) means = "Bus";
          if (trans == 2) means = "Plane";
          System.out.println(String.format("%s -> %s, time -> %d, fee -> %d, means -> %s", src, dst, time, fee, means));
        }
        System.out.println();
      }
      ctx.html(htmlRoute.getRouteListHtml(roots, date, depTime));
    });


    app.post("/make-reservation", ctx -> {
      reservations.put(ctx.formParam("day"), ctx.formParam("time"));
      ctx.html("<h1>Your reservation has been saved</h1>");
    });

    app.get("/check-reservation", ctx -> {
      ctx.html(reservations.get(ctx.queryParam("day")));
    });

    app.post("/book0", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(0), date, depTime, 0));
    });
    app.post("/book1", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(1), date, depTime, 1));
    });
    app.post("/book2", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(2), date, depTime, 2));
    });
    app.post("/book3", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(3), date, depTime, 3));
    });
    app.post("/book4", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(4), date, depTime, 4));
    });
    app.post("/book5", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(5), date, depTime, 5));
    });
    app.post("/book6", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(6), date, depTime, 6));
    });
    app.post("/book7", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(7), date, depTime, 7));
    });
    app.post("/book8", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(8), date, depTime, 8));
    });
    app.post("/book9", ctx -> {
      ctx.html(htmlRoute.getReservedListHtml(roots.get(9), date, depTime, 9));
    });

  }
}

