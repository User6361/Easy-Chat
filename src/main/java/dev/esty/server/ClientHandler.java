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

    public void handleClient(){

        try (DataInputStream in = new DataInputStream(client.getInputStream())){
            //fist message from user - nickname
            NickNameUser = in.readUTF();
            print(NickNameUser + " is connected to server!");
            ClientMessages cm = new ClientMessages(client, NickNameUser);
            cm.handleMessage();
        } catch (SocketException e) {
            print("Somthing wrong with user connection");
            //e.printStackTrace();
        } catch(IOException e){
            print("Client disconected durind registration!");
            e.printStackTrace();
        }
    }
}