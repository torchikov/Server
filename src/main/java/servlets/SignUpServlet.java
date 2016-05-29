package servlets;

import baseLogic.AccountService;
import dataSets.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType(setContentTypeText());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UserDataSet userDataSet = accountService.getUser(login);
        if (userDataSet != null) {
            response.setContentType(setContentTypeText());
            response.getWriter().println("<html><body>Пользователь с таким именем существует!</body></html>");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            accountService.addUser(new UserDataSet(login, password));
            response.sendRedirect("/chat.html");
        }


    }

    private String setContentTypeText() {
        return "text/html;charset=utf-8";
    }

}
