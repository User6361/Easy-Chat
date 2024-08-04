package dev.esty.server;

import dev.estuvium.utilities.GetTime;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        GetTime t = new GetTime();

        try (ServerSocket server = new ServerSocket(10000)) {

            System.out.println(t.getTime() + "Server started listening on port 10000");
            while (true) {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.handleClient();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}