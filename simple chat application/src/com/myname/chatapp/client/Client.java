package com.myname.chatapp.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        new Thread(new ReadMessages(socket)).start();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            out.println(message);
        }
    }
}

class ReadMessages implements Runnable {
    private BufferedReader in;

    public ReadMessages(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}