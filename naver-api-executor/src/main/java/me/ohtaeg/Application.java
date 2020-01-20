package me.ohtaeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //changeEmbeddedTomcat();
    }

    private static void changeEmbeddedTomcat()   { //throws LifecycleException
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8081);
//
//        Context context = tomcat.addContext("/", "/");
//        HttpServlet servlet = new HttpServlet() {
//            @Override
//            protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.println("<html>");
//                writer.println("<head>");
//                writer.println("    <title>mission1</title>");
//                writer.println("</head>");
//                writer.println("<body>hello world</body>");
//                writer.println("</html>");
//
//            }
//        };
//
//        String servletName = "apiServlet";
//        tomcat.addServlet("/", servletName, servlet);
//        context.addServletMappingDecoded("/api", servletName);
//
//        tomcat.getConnector();
//        tomcat.start();
//        tomcat.getServer().await();
    }
}
