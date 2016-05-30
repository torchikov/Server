package servlets;

import baseLogic.ChatService;
import chat.ChatServiceImp;
import chat.ChatWebSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    static Logger logger = LogManager.getLogger(WebSocketChatServlet.class.getName());
    public static final String PAGE_URL = "/chat";
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatServiceImp();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((request, response) -> new ChatWebSocket(chatService));
    }
}
