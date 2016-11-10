import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Created by Yufei Xu on 10/22/2016.
 *implements network programming bits, application protocol
 * users input
 */
public class foilmakerController{
    static Socket socket;
    public static String reply;

    public static void main(String[] args) throws IOException {
        foilmakerView f = new foilmakerView();
        int serverPort;

        String serverIP = "localhost";
        serverPort = 5000;


        try {
            socket = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            System.err.println("Error with IO:\n" + e.getMessage());
            System.exit(3);


        }
f.run();

}



    public static String sendmessage(String inputLine) throws IOException {
        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outToServer.println(inputLine);


        reply = null;
        while (reply == null) {
            try {
                reply = inFromServer.readLine();
                System.out.println(reply);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            }

        }

        return reply;
    }

    public static String recieve() throws IOException {

        socket.setSoTimeout(1000);
        String a = null;
        while(a==null){
            try {
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                reply = inFromServer.readLine();
                a = reply;
            }catch (SocketTimeoutException e){

            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        socket.setSoTimeout(1000);
        System.out.println(a);
        return a;
    }

}
