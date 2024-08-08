package dev.esty.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import static dev.esty.server.Main.getTime;
import static dev.esty.server.Main.stop;

public class ClientHandler {
    // non-finale NickNameUser needed to create global variable for no-govnokofing
    private String NickNameUser;
    private final Socket client;

    public ClientHandler(Socket client){
        this.client = client;
    }

    public void handleClient() throws IOException{
        try (DataInputStream in = new DataInputStream(client.getInputStream())){

            NickNameUser = in.readUTF();
            System.out.println(getTime() + NickNameUser + " is connected to chat!");
            ClientMessages cm = new ClientMessages(client, NickNameUser);
            cm.handleMessage();

        } catch (SocketException e) {
            System.out.println(getTime() + "Something wrong with user connection!");
            //e.printStackTrace();
        } finally {
            //if(!client.isClosed()) client.close();
            stop();
        }
    }
}