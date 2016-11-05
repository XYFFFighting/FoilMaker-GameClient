import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Yufei Xu on 10/22/2016.
 *implements network programming bits, application protocol
 * users input
 */
public class foilmakerController {
    static Socket socket;
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

//test GUI
        //f.setSize(300,500);

        //f.Login();
        //f.Login2();
        //f.JoinGame();
        //f.StartnewGame();
        //f.Waiting();
        //f.Suggestionwords();
        //f.pickoption();
        //f.receiveResults();
        //f.setVisible(true);
    }
}




