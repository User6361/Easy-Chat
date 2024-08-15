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
                    toString("["+NickNameUser + "]: " + entryFromUser);
                } catch (EOFException | SocketException e) {
                    toString("Client " + NickNameUser + " disconnecting...");
                    break;
                }
            }
            in.close();
            client.close();
            toString("Client " + NickNameUser + " disconnected");
        } catch (IOException e) {
            toString("Client " + NickNameUser + " disconnected uneceptably!");
        }

    }
}