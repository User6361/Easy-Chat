package dev.esty.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Main{
    private String NickNameUser;
    private final Socket client;

    public ClientHandler(Socket client){
        this.client = client;
    }

    public void handleClient() throws IOException{

        try (DataInputStream in = new DataInputStream(client.getInputStream())){
            //fist message from user - nickname
            NickNameUser = in.readUTF();
            toString(NickNameUser + " is connected to chat!");
            ClientMessages cm = new ClientMessages(client, NickNameUser);
            cm.handleMessage();
        } catch (SocketException e) {
            toString("Something wrong with user connection!");
            //e.printStackTrace();
        } finally {
            if(!client.isClosed()) client.close();
        }

    }
}