package dev.esty.server;

import dev.estuvium.utilities.GetTime;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientMessages{
    private final Socket client;
    private final String NickNameUser;
    GetTime t = new GetTime();

    public ClientMessages(Socket client, String NickNameUser){
        this.client = client;
        this.NickNameUser = NickNameUser;
    }

    public void handleMessage(){
        try (DataInputStream in = new DataInputStream(client.getInputStream())) {
            while (!client.isClosed()) {
                try {
                    String entryFromUser = in.readUTF();
                    System.out.println(t.getTime() + "$["+NickNameUser + "]: " + entryFromUser);
                } catch (EOFException | SocketException e) {
                    System.out.println(t.getTime() + "Client " + NickNameUser + " disconnecting...");
                    break;
                }
            }
            in.close();
            client.close();
            System.out.println(t.getTime() + "Client " + NickNameUser + " is disconnected!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}