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

public class SignInServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(SignInServlet.class.getName());
    private final AccountService accountService;
    public static final String PAGE_URL = "/signin";

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        if (login == null || password == null){
            logger.info("Не передан логин или пароль");
            response.setContentType(setContentTypeText());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserDataSet userDataSet = accountService.getUser(login);

        if (userDataSet == null || !userDataSet.getPassword().equals(password)){
            logger.info("Пользователь " + userDataSet + " не найден!");
            response.setContentType(setContentTypeText());
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } else {
            String sessionId = request.getSession().getId();
            accountService.addUserSession(sessionId, userDataSet);
            logger.info("Залогинился пользователь " + userDataSet);
            response.setContentType(setContentTypeText());
            response.sendRedirect("/chat.html");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private String setContentTypeText() {
        return "text/html;charset=utf-8";
    }
}
