package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graph {
     static JTextArea text;
    private static JTextField text1;
    private Server server;
    private ClientServer client;


    private void go(){
        try {
            Thread one = new Thread(new ThreadServer());
            Thread two = new Thread(new ThreadClient());
            one.start();
            one.sleep(2000);
            two.start();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Font font = new Font("egor", Font.ITALIC, 22);
        Font font1 = new Font("egor", Font.PLAIN, 20);
        text = new JTextArea(15, 20);
        text.setEditable(false);
        text.setFont(font);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        text1 = new JTextField(30);
        text1.setFont(font1);
        JButton button = new JButton("Send");
        button.addActionListener(new Send());

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Открыть");
        JMenuItem item = new JMenuItem("Запуск сервера");
        JMenuItem item1= new JMenuItem ("Запуск чата");

        bar.add(menu);
        menu.add(item);
        menu.add(item1);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.add(text);
        panel.add (text1);
        panel.add(button);
        frame.setSize(700,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(bar);
   }
    public static void main(String[] args) {
         Graph one = new Graph();
         one.go();
    }
    private class ThreadServer implements Runnable{
        public void run(){
            server = new Server();
            server.go();
        }
}
    private class ThreadClient implements Runnable{
        public void run(){
            client = new ClientServer();
            client.go();
        }
}
    private class Send implements ActionListener {
        public void actionPerformed(ActionEvent ev){
            try {
                String message = text1.getText();
                System.out.println(text1.getText());
                client.getMess(message);
                text1.setText(" ");
                text1.requestFocus();
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
}