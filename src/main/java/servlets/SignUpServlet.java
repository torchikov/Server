package servlets;

import baseLogic.AccountService;
import dataSets.UserDataSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(SignUpServlet.class.getName());
    public static final String PAGE_URL = "/signup";
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
        UserDataSet user = accountService.getUser(login);
        if (user != null) {
            response.setContentType(setContentTypeText());
            response.getWriter().println("<html><body>Пользователь с таким именем существует!</body></html>");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            user = new UserDataSet(login, password);
            accountService.addUser(user);
            logger.info("Зарегистрировался пользователь " + user);
            response.sendRedirect("/chat.html");
        }


    }

    private String setContentTypeText() {
        return "text/html;charset=utf-8";
    }

}
