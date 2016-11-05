import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Yufei Xu on 11/2/2016.
 */
public class foilmakerClient {
    public static String reply;

    public static void sendmessage(String inputLine) throws  IOException {

        Socket socket =foilmakerController.socket;
        PrintWriter outToServer = null;
        BufferedReader inFromServer = null;

        Scanner scanner = new Scanner(System.in);



        try {
            // Connect to server
            //System.out.println("Enter the server port");
            //serverPort = scanner.nextInt();
            //socket = new Socket(serverIP, serverPort);
            OutputStream os = socket.getOutputStream();
            outToServer = new PrintWriter(os);
            InputStream is = socket.getInputStream();
            inFromServer = new BufferedReader(new InputStreamReader(is));
            outToServer.println(inputLine);
            outToServer.flush();
            //socket.shutdownOutput();
            reply=inFromServer.readLine();

                if (!((reply==null))){
                System.out.println("Recieved mesaage："+reply);
            }
        } catch (MalformedURLException e) {
                System.err.println("Error with URL:\n" + e.getMessage());
                System.exit(2);
            } catch (IOException e) {
                System.err.println("Error with IO:\n" + e.getMessage());
                System.exit(3);
            } /*
            finally {
                if(socket != null)
                    socket.close();
                if(outToServer != null)
                    outToServer.close();
                if(inFromServer != null)
                    inFromServer.close();

            }
            */
    }



}
