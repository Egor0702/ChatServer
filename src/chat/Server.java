package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

 class Server {
     private PrintWriter print;
     private BufferedReader buff;

     public void go(){
        new Server().setServer();

    }
     private void setServer(){
        try{
            ServerSocket server = new ServerSocket(4000);
            while(true){
                Socket socket = server.accept();
                InputStreamReader input = new InputStreamReader(socket.getInputStream());
                buff = new BufferedReader (input);
                print = new PrintWriter (socket.getOutputStream());
                Thread t = new Thread(new MessagePrint());
                t.start();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
     private class MessagePrint implements Runnable {
        public void run(){
            try{
                String message;
                while ((message = buff.readLine()) != null){
                    print.println(message);
                    print.flush();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

}

