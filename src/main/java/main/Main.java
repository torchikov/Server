package main;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestServlet allRequestServlet = new AllRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        System.out.println("Server started");
        server.start();
        server.join();
    }
}
