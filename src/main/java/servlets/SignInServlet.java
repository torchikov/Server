package servlets;

import BaseLogic.AccountService;
import dataSets.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null){
            response.setContentType(setContentTypeText());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserDataSet userDataSet = accountService.getUser(login);

        if (userDataSet == null || !userDataSet.getPassword().equals(password)){
            response.setContentType(setContentTypeText());
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.setContentType(setContentTypeText());
            response.sendRedirect("/chat.html");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    private String setContentTypeText() {
        return "text/html;charset=utf-8";
    }
}
