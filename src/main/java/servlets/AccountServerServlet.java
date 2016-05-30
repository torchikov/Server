package servlets;


import baseLogic.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServerServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(AccountServerServlet.class.getName());
    public static final String PAGE_URL = "/admin";
    private AccountServer accountServer;

    public AccountServerServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(setContentTypeText());
        response.getWriter().println(accountServer.getUserLimit());
        response.setStatus(HttpServletResponse.SC_OK);
    }


    private String setContentTypeText() {
        return "text/html;charset=utf-8";
    }
}
