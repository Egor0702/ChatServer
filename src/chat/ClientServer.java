package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientServer {
    private PrintWriter print;
    private BufferedReader buffer;
    private Socket socket;

        protected void go(){
            setSock();
        }
        private void setSock(){
            try{
                socket = new Socket("localhost", 4000);
                InputStreamReader input = new InputStreamReader (socket.getInputStream());
                buffer = new BufferedReader (input);
                print = new PrintWriter(socket.getOutputStream());
                Thread t = new Thread(new ThreadClient());
                t.setName("Егор");
                t.start();

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        protected void getMess(String ret){
            try{
                print.println(ret);
                print.flush();
            }catch (Exception ex){ex.printStackTrace();}
        }

    private class ThreadClient implements Runnable{
        public void run() {
            try {
                String message;
                while ((message = buffer.readLine()) != null) {
                Graph.text.append(Thread.currentThread().getName() + " : " + message + "\n");
                }
                }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
    }

