package dev.esty.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {


    private ServerSocket server;
    private static Socket client;

    public static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:HH:mm:ss");
        String formattedNow = now.format(formatter);
        return "[" + formattedNow + "] ";
    }

    public void start(int socket) {
        try{
            server = new ServerSocket(socket);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop() {
        try(DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());){
            in.close();
            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void main(String[] args) throws IOException {

        /*try (ServerSocket server = new ServerSocket(10000)) {

            System.out.println(getTime() + "Server started listening on port 10000");


            while (true) {
                client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.handleClient();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        start(10000);
        System.out.println("Server started in port 10000");
        ClientHandler ch = new ClientHandler(client);
        ch.handleClient();
        stop();
    }
}