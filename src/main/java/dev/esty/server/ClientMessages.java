package dev.esty.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import static dev.esty.server.Main.getTime;


public class ClientMessages {
    private final Socket client;
    private final String NickNameUser;


    public ClientMessages(Socket client, String NickNameUser) {
        this.client = client;
        this.NickNameUser = NickNameUser;
    }

    public void handleMessage() {
        try (DataInputStream in = new DataInputStream(client.getInputStream())){

            while (!client.isClosed()) {
                try {
                    String entryFromUser = in.readUTF();
                    System.out.println(getTime() + "$[" + NickNameUser + "]: " + entryFromUser);



                } catch (EOFException | SocketException e) {
                    System.out.println(getTime() + "Client " + NickNameUser + " disconnecting...");
                    break;
                }
            }
            in.close();
            client.close();
            System.out.println(getTime() + "Client " + NickNameUser + " is disconnected!");

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}