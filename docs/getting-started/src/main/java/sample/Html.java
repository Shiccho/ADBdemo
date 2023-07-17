package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public class Html {
    String template; 
    public Html(String templateFile) {
        Path path = Paths.get("src/main/resources/public/"+templateFile);
        try {
            this.template = Files.readString(path);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    String getHtml(String... values) {
        String html;
        if (values.length != 0) {
            html = String.format(this.template, (Object[])values);
        } else {
            html = this.template;
        }
        return html;
    }
    String getRouteListHtml(ArrayList<ArrayList<MyPath>> roots, String date, String time) {
        // Calc Data
        ArrayList<Integer> totalTime = new ArrayList<Integer>();
        ArrayList<Integer> totalFee = new ArrayList<Integer>();
        ArrayList<Integer> connections = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> cities = new ArrayList<ArrayList<String>>();
        Date dateDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for (ArrayList<MyPath> root : roots) {
            int tt = 0, tf = 0;
            for (MyPath edge : root) {
                int nowTime = edge.getTime();
                int nowFee = edge.getFee();
                tt += nowTime;
                tf += nowFee;
            }
            totalTime.add(tt);
            totalFee.add(tf);
            connections.add(root.size()-1);
        }

        String container = "<!DOCTYPE html> \n" +
                           "<html lang=\"ja\"> \n" +
                           "<head>  \n" +
                           "<meta charset=\"UTF-8\"> \n" +
                           "<title>RouteList</title>  \n" +
                           "<link rel=\"stylesheet\" href=\"./route.css\"> \n" +
                           "</head> \n" +
                           "<h1>Route List</h1> \n" +
                           "<div class=\"container add-control\">\n";
        
        for (int i = 0; i < roots.size(); i++) {
            String tmp = String.format(
                "<input type=\"radio\" id=\"tab%d\" class=\"radio\" name=\"tab\" checked=\"checked\"><label class=\"tab-title title%d\" for=\"tab%d\"> \n" +
                "<table> \n" +
                    "<tr> \n" +
                        "<td>%d min</td><br> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                        "<td>%d yen</td><br> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                        "<td>%d transfer</td> \n" + 
                    "</tr> \n" +
                "</table> \n" +
                "</label>\n"
                ,i+1, i+1, i+1, totalTime.get(i), totalFee.get(i), connections.get(i));
            container = container + tmp;
        }

        container = container +  "<div class=\"tab-body\">\n";
        // for (ArrayList<MyPath> root: roots){
        for (int i = 0; i < roots.size(); i++){
            ArrayList<MyPath> root = roots.get(i);
            int elapsedTime = 0;
            int size = root.size();

            String body = String.format("<div class=\"body%d\">\n" +
                                "<table>\n"
                        ,i+1);

            String start = String.format(
                            "<tr> \n" +
                            "<td>%s </td> \n" +
                            "<td>%s </td> \n" +
                            "</tr>"
                        , root.get(0).getSrc(), date+" " +time);
            
            elapsedTime += root.get(0).getTime();
            
            String means = "";
            for (int j = 1; j < size; j++) {
                int trans = root.get(j).getTrafficroot();

                if (trans == 0) means = "Train";
                else if (trans == 1) means = "Bus";
                else means = "Plane";

                String tmp = String.format(
                            "<tr> \n" +
                            "<td>|</td> \n" +
                            "<td>%s </td> \n" +
                            "</tr> \n" +
                            "<tr> \n" +
                            "<td>%s</td> \n" +
                            "<td>%s min</td> \n" +
                            "</tr>"
                        , means, root.get(j).getSrc(), Integer.valueOf(elapsedTime).toString());

                start += tmp;
                elapsedTime += root.get(j).getTime();
            }

            try{
                dateDate = sdFormat.parse(date+" "+time);
                calendar = Calendar.getInstance();
                calendar.setTime(dateDate);
                calendar.add(Calendar.MINUTE, elapsedTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Date resultDate = calendar.getTime();
            String resultString = sdFormat.format(resultDate);
            String end = String.format(
                            "<tr> \n" +
                                "<td>|</td> \n" +
                                "<td>%s</td> \n" +
                            "</tr> \n" +
                            "<tr> \n" +
                                "<td>%s</td> \n" +
                                "<td>%s min</td> \n" +
                            "</tr> \n" +
                            "<td>ETA : %s </td> \n" +
                            "<form method=\"post\" action=\"/book%d\"\n" +
                            "<td><button>book</button></td>\n" + 
                            "</form>\n" + 
                            "</table> \n" +
                            "</div>"
                        , means, root.get(size-1).getDst(), Integer.valueOf(elapsedTime).toString(), resultString, i);

            start += end;
            body += start;

            container = container + body;

            // container += "<button>Submit</button>";
        }
        
        container += "</div> \n" + 
                     "</div> \n" +
                     "<br> \n" +
                     "<button>back</button>";

        return container;
    }



    String getReservedListHtml(ArrayList<MyPath> root, String date, String time, int num) {
        // Calc Data
        Date dateDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            int tt = 0, tf = 0;
            for (MyPath edge : root) {
                int nowTime = edge.getTime();
                int nowFee = edge.getFee();
                tt += nowTime;
                tf += nowFee;
            }

        String container = "<!DOCTYPE html> \n" +
                           "<html lang=\"ja\"> \n" +
                           "<head>  \n" +
                           "<meta charset=\"UTF-8\"> \n" +
                           "<title>ReservedList</title>  \n" +
                           "<link rel=\"stylesheet\" href=\"./route.css\"> \n" +
                           "</head> \n" +
                           "<h1>Your Reserved List</h1> \n" +
                           "<div class=\"container add-control\">\n";
        
            String tmp = String.format(
                "<input type=\"radio\" id=\"tab%d\" class=\"radio\" name=\"tab\" checked=\"checked\"><label class=\"tab-title title%d\" for=\"tab%d\"> \n" +
                "<table> \n" +
                    "<tr> \n" +
                        "<td>%d min</td><br> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                        "<td>%d yen</td><br> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                        "<td>%d transfer</td> \n" + 
                    "</tr> \n" +
                "</table> \n" +
                "</label>\n"
                ,num+1, num+1, num+1, tt, tf, root.size()-1);
            container = container + tmp;

        container = container +  "<div class=\"tab-body\">\n";
            int elapsedTime = 0;
            int size = root.size();

            String body = String.format("<div class=\"body%d\">\n" +
                                "<table>\n"
                        ,num+1);

            String start = String.format(
                            "<tr> \n" +
                            "<td>%s </td> \n" +
                            "<td>%s </td> \n" +
                            "</tr>"
                        , root.get(0).getSrc(), date+" " +time);
            
            elapsedTime += root.get(0).getTime();
            
            String means = "";
            for (int j = 1; j < size; j++) {
                int trans = root.get(j).getTrafficroot();

                if (trans == 0) means = "Train";
                else if (trans == 1) means = "Bus";
                else means = "Plane";

                String tmp1 = String.format(
                            "<tr> \n" +
                            "<td>|</td> \n" +
                            "<td>%s </td> \n" +
                            "</tr> \n" +
                            "<tr> \n" +
                            "<td>%s</td> \n" +
                            "<td>%s min</td> \n" +
                            "</tr>"
                        , means, root.get(j).getSrc(), Integer.valueOf(elapsedTime).toString());

                start += tmp1;
                elapsedTime += root.get(j).getTime();
            }

            try{
                dateDate = sdFormat.parse(date+" "+time);
                calendar = Calendar.getInstance();
                calendar.setTime(dateDate);
                calendar.add(Calendar.MINUTE, elapsedTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Date resultDate = calendar.getTime();
            String resultString = sdFormat.format(resultDate);
            String end = String.format(
                            "<tr> \n" +
                                "<td>|</td> \n" +
                                "<td>%s</td> \n" +
                            "</tr> \n" +
                            "<tr> \n" +
                                "<td>%s</td> \n" +
                                "<td>%s min</td> \n" +
                            "</tr> \n" +
                            "<td>ETA : %s </td> \n" +
                            "</table> \n" +
                            "</div>"
                        , means, root.get(size-1).getDst(), Integer.valueOf(elapsedTime).toString(), resultString);

            start += end;
            body += start;

            container = container + body;

        
        container += "</div> \n" + 
                     "</div> \n" +
                     "<br> \n" +
                     "<button>back</button>";

        return container;
    }
}
