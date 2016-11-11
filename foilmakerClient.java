import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Yufei Xu on 11/2/2016.
 */
public class foilmakerClient {}
    //public static String reply;
/*
    // public static String reply2;

    public static String sendmessage(String inputLine) throws IOException {
        Socket socket = foilmakerController.socket;

        PrintWriter outToServer = null;
        BufferedReader inFromServer = null;

        Scanner scanner = new Scanner(System.in);


        // Connect to server
        //System.out.println("Enter the server port");
        //serverPort = scanner.nextInt();
        //socket = new Socket(serverIP, serverPort);
        OutputStream os = socket.getOutputStream();
        outToServer = new PrintWriter(os, true);
        InputStream is = socket.getInputStream();
        inFromServer = new BufferedReader(new InputStreamReader(is));
        outToServer.println(inputLine);

        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outToServer.println(inputLine);
        //socket.setSoTimeout(1);
        //reply=inFromServer.readLine();
        reply = null;
        while (reply == null) {
            try {
                reply = inFromServer.readLine();
                System.out.println(reply);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }

        }
        socket.setSoTimeout(1000);

/*            if((reply=inFromServer.readLine())!=null) {
                System.out.println("Recieved mesaage：" + reply);


            }

return reply;
    }
    public static String reply(){
        return reply;
    }
}
*/