package dev.esty.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientMessages extends Main{
    private Socket client;
    private final String NickNameUser;


    public ClientMessages(Socket client, String NickNameUser) {
        this.client = client;
        this.NickNameUser = NickNameUser;
    }

    public void handleMessage() {

        try (DataInputStream in = new DataInputStream(client.getInputStream())) {
            while (!client.isClosed()) {
                try {
                    String entryFromUser = in.readUTF();
                    print("[" + NickNameUser + "]: " + entryFromUser);
                } catch (EOFException | SocketException e) {
                    print("Client " + NickNameUser + " disconnecting...");
                    break;
                }
            }
            in.close();
            client.close();
            print("Client " + NickNameUser + " disconnected");
        } catch (IOException e){
            print("Client " + NickNameUser + " disconnected uneceptably!");
        }

    }
}