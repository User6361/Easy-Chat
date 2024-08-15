package dev.esty.client;

import java.io.*;
import java.net.Socket;

public class ClientSend{
    public static void main(String[] args){
        try(Socket client = new Socket("147.45.141.37", 10000)){
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.print("Enter Nick Name for user: ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String nickName = br.readLine();
            out.writeUTF(nickName);

            while (true){
                System.out.print(nickName + ": ");
                String entryUser = br.readLine();
                out.writeUTF(entryUser);
                if (entryUser.equals("exit")){
                    System.out.println("Disconnected from server");
                    break;
                }

            }
            client.close();
            in.close();
            out.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}