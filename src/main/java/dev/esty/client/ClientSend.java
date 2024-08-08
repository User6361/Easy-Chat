//for send messages to server

package dev.esty.client;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;


public class ClientSend extends Thread{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);


        //подключаемся к серверу
        try(Socket socket = new Socket("147.45.141.37", 10000)){
            System.out.println("You are connected to the server!");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());


            //создаем поток на прослушивание ответа от сервера
            //если ответа нет то мы выходим с программы
            //грубо говоря проверяем на работоспособность сервера
            Thread listenThread = new Thread(() -> {
                try {
                    while (true) {
                        in.readUTF();
                        //System.out.println("Received from server: " + response);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost!");
                    System.exit(0);
                    
                }

            });
            //лучше зарание включать прослушку чтобы если что выкидывало тебя
            listenThread.start();

            //первым сообщением от клиента будет данные о его никнейме
            //может и говнокод но я пока не знаю что другое делать
            //обезательно записать в переменную чтобы потом использоваться
            System.out.print("Enter Nick Name: ");
            String nickName = sc.nextLine();
            out.writeUTF(nickName);
            //создаем переменную ввода данных от пользователя
            String entryToServer;

            while(true){
                try{
                    //ввод данных с клавиатуры
                    System.out.print("user@" + nickName + ": ");
                    entryToServer = sc.nextLine();
                    //отпровляем информацию на сервере
                    out.writeUTF(entryToServer);

                    if(entryToServer.equals("/exit")){
                        out.flush();
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Server connection exception!");
                    out.flush();
                    socket.close();
                    break;
                    //e.printStackTrace();
                }

                socket.close();
            }
            

        }catch (Exception e){
            System.out.println("Server is down!");
        }
    }
}
