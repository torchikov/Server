package BaseLogic;


import chat.ChatWebSocket;

public interface ChatService {
    void sendMessage(String message);

    void add(ChatWebSocket webSocket);

    void remove(ChatWebSocket webSocket);
}
