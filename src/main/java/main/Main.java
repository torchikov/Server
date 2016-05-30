package main;


import accounts.AccountServerController;
import accounts.AccountServerImpl;
import accounts.AccountServiceImpl;
import baseLogic.AccountServer;
import baseLogic.AccountServerControllerMBean;
import baseLogic.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AccountServerServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import java.lang.management.ManagementFactory;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            logger.error("Use port as first argument");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        logger.info("Starting at http://127.0.0.1:" + args[0]);

        AccountService accountService = new AccountServiceImpl();

        AccountServer accountServer = new AccountServerImpl(10);

        AccountServerController serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        final StandardMBean mBean = new StandardMBean(serverStatistics, AccountServerControllerMBean.class);
        mbs.registerMBean(mBean, name);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), SignUpServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), SignInServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), WebSocketChatServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new AccountServerServlet(accountServer)), AccountServerServlet.PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(port);
        server.setHandler(handlers);

        System.out.println("Server started");
        server.start();
        logger.info("Server started");
        server.join();
    }
}
