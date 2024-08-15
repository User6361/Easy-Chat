package dev.esty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Main extends Object{

    private static Socket client;

    public static void toString(String entry){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:HH:mm:ss");
        String formattedNow = now.format(formatter);
        System.out.println(formattedNow + " " + entry);

    }
    public static void main(String[] args){
        try(ServerSocket socket = new ServerSocket(10000)){
            toString("Server started in port 10000");
            while (true){
                client = socket.accept();
                ClientHandler ch = new ClientHandler(client);
                ch.handleClient();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
