package chat;


import BaseLogic.ChatService;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImp implements ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatServiceImp() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void sendMessage(String message) {
        for (ChatWebSocket user : webSockets) {
            user.sendString(message);
        }
    }

    @Override
    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    @Override
    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
