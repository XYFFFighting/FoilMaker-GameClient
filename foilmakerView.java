import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Yufei Xu on 10/22/2016.
 * implements Java GUI classes
 * program out put
 */
public class foilmakerView extends JFrame implements ActionListener{
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();
    private String send;
    private String Name;
    private String usertoken;
    private String key;
    private String msg;
    private String msg1;
    private String participants;
    private String question;
    private String[] options;
    private int numJoin;
    private boolean startGame=false;
    private boolean allsuggest=false;
    JButton SG = new JButton("Start Game");
    JTextArea a = createTextArea(5,1, Color.YELLOW,"");
    JTextArea b =createTextArea(5,23,Color.orange,"");
    JTextArea c = createTextArea(12,23,Color.yellow,"");
    JLabel Newusercreated = new JLabel("Welcome");
    public foilmakerView(){
        String send = this.send;
        String Name = this.Name;
        String usertoken = this.usertoken;
        String key = this.key;
    }

public void run(){
    mainPanel.setLayout(layout);
    mainPanel.add(Login(),"1");
    mainPanel.add(Login2(),"2");
    mainPanel.add(StartnewGame(),"3");
    mainPanel.add(JoinGame(),"4");
    mainPanel.add(Waiting(),"5");
    mainPanel.add(Suggestionwords(),"6");
    mainPanel.add(receiveResults(),"8");
    add(mainPanel);
    setLocation(300,500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        send="LOGOUT--";
        try{
            foilmakerController.send(send);
        }catch (IOException e1){
            e1.printStackTrace();
        }
        super.windowClosing(e);
    }
});
    pack();
    layout.show(mainPanel,"1");
    setVisible(true);
}


//LOGIN GUI
    public JPanel Login(){
        JButton loginButton, Register;
        final JLabel Username, Password, foilMaker;

        this.setTitle("FoilMaker!");

        Username = J("Username");
        Password = J("Password");
        foilMaker = J("FoilMaker!");



        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel pN = new JPanel(new FlowLayout());
        pN.add(foilMaker);


        JPanel pC = new JPanel(new BorderLayout());
        //pC.setBorder(BorderFactory.createTitledBorder(""));

        final JPanel login = new JPanel(new FlowLayout());
        login.add(Username);
        final JTextField input = new JTextField(10);
        JPanel t1 = new JPanel(new GridBagLayout());
        t1.add(input);
        login.add(t1);


        JPanel login3 = new JPanel(new FlowLayout());
        login3.add(Password);
        final JPasswordField pass = inVisiblecreateText();
        login3.add(pass);

        JPanel login2 = new JPanel(new GridLayout(0,1));

        login2.setBorder(BorderFactory.createTitledBorder(""));
        login2.add(login);
        login2.add(login3);

        Register = new JButton("Register");
        Register.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = input.getText();
                        String password = String.valueOf(pass.getPassword());
                        send="CREATENEWUSER"+"--"+name+"--"+password;

                        try {
                            foilmakerController.sendmessage(send);
                            msg = foilmakerController.recieve();
                            if(getStatus(msg).equals("SUCCESS")) {
                                Newusercreated = new JLabel("New user created");
                            }
                            else{
                                Newusercreated = new JLabel("Wrong");
                            }
                                mainPanel.add(Login(),"1");
                                layout.show(mainPanel,"1");

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }


                    }
                }
        );
        loginButton = new JButton("Login");
        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String name = input.getText();
                        String password = String.valueOf(pass.getPassword());
                        send="LOGIN"+"--"+name+"--"+password;
                        try{
                          foilmakerController.sendmessage(send);
                            msg=foilmakerController.recieve();
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }
                        if(getStatus(msg).equals("SUCCESS")){

                            usertoken = getMessage(msg,3);
                            Name = name;
                            mainPanel.add(Login2(),"2");
                            layout.show(mainPanel,"2");
                            mainPanel.add(StartnewGame(),"3");
                            mainPanel.add(JoinGame(),"4");
                            mainPanel.add(Waiting(),"5");
                            mainPanel.add(Suggestionwords(),"6");
                            mainPanel.add(receiveResults(),"8");
                        }
                        else
                            System.out.println(getStatus(msg));
                        
                    }
                }
        );


        JPanel loginbutton = new JPanel(new GridBagLayout());
        loginbutton.add(loginButton);
        loginbutton.add(Register);

        pC.add(login2, BorderLayout.NORTH);
        pC.add(loginbutton, BorderLayout.CENTER);
        //pC.add(pN, BorderLayout.NORTH);


        panel.add(pC);

        JPanel pS = new JPanel(new GridLayout(0,3));

        pS.add(Newusercreated);

        c1.add(pN,BorderLayout.NORTH);
        c1.add(panel, BorderLayout.CENTER);
        c1.add(pS, BorderLayout.SOUTH);

return c1;
    }

    public JPanel Login2(){
        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob will come from Controller. J is a method
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JButton SNG = new JButton("Start New Game");
        SNG.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){

                        send = "STARTNEWGAME"+"--"+usertoken;

                        try{
                          foilmakerController.sendmessage(send);
                            msg=foilmakerController.recieve();
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }
                        if(getStatus(msg).equals("SUCCESS")){
                            key = getMessage(msg,3);
                            mainPanel.add(StartnewGame(),"3");
                            layout.show(mainPanel,"3");
//                            mainPanel.add(JoinGame(),"4");
//                            mainPanel.add(Waiting(),"5");
//                            mainPanel.add(Suggestionwords(),"6");
//                            mainPanel.add(pickoption(),"7");
//                            mainPanel.add(receiveResults(),"8");

                           SwingWorker worker=new SwingWorker() {
                               @Override
                               protected Object doInBackground() throws Exception {
                                    { while(startGame!=true){
                                        try {
                                            participants = null;
                                            msg1 = foilmakerController.recieve();
                                            //System.out.println("out"+msg1);
                                            if (startGame == false){
                                                participants = getMessage(msg1, 1);
                                            if (participants != null) {
                                                a.append(participants + "\n");
                                                mainPanel.add(StartnewGame(), "3");
                                                layout.show(mainPanel, "3");
                                                SG.setEnabled(true);
                                            }
                                        }
                                        if(startGame==true){
                                            msg=msg1;
                                            question = getMessage(msg,1);
                                            mainPanel.add(Suggestionwords(),"6");
                                            layout.show(mainPanel, "6");
                                        }
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                            ;
                                        }
                                    }

                                   }
                               return null;}
                           };

                           worker.execute();



                        }

                        else
                            System.out.println(getStatus(msg));

                    }
                }
        );
        JButton JaG = new JButton("Join a Game");
        JaG.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        layout.show(mainPanel,"4");

                    }
                }
        );

        JPanel pC = new JPanel(new GridBagLayout());
        pC.add(SNG);
        pC.add(JaG);

        JPanel pS = new JPanel(new GridLayout(0,3));
        JLabel Welcom = J("Welcome!");
        pS.add(Welcom);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS, BorderLayout.SOUTH);
return c1;
    }
    //when press the SNG Button

    public JPanel StartnewGame(){//when press the SNG button

        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());


        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("Others should use this key to join your game");
        JPanel pC = new JPanel(new GridLayout(0,1));
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1, s);
        JTextField t = new JTextField(key);
        t.setEditable(false);

        pC1.add(t);
        pC.add(pC1);
        SG = new JButton("Start Game");

        SG.setEnabled(false);

        SG.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startGame=true;
                        send = "ALLPARTICIPANTSHAVEJOINED--" + usertoken + "--" + key;//key will come from server

                        try {

                            foilmakerController.sendmessage(send);
                            //msg=foilmakerController.recieve();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }



                    }
                }
        );
        JPanel participants = new JPanel(new BorderLayout());
        participants.setBorder(BorderFactory.createTitledBorder("Participants"));

        participants.add(a);
        pC.add(participants);

        JPanel pC2 = new JPanel(new GridBagLayout());
        pC2.add(SG);
        pC.add(pC2);





        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Game started: You are the leader");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS, BorderLayout.SOUTH);

return c1;


    }//start a new game

    public JPanel JoinGame(){//when pree the join a game botton
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("Enter the game key to join a game");
        JPanel pC = new JPanel(new GridLayout(0,1));
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1, s);
final JTextField t1 = createText(4);
        pC1.add(t1);
        pC.add(pC1);

        JButton SG = new JButton("Join Game");
        SG.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        key = t1.getText();
                        send = "JOINGAME--" + usertoken + "--" + key;
                        try {
                            foilmakerController.sendmessage(send);
                            msg=foilmakerController.recieve();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if (getStatus(msg).equals("SUCCESS")) {
                            layout.show(mainPanel, "5");




                            msg = null;
                            SwingWorker worker = new SwingWorker() {
                                @Override
                                protected Object doInBackground() throws Exception {
                                    try {
                                        msg = foilmakerController.recieve();
                                        question = getMessage(msg, 1);
                                        mainPanel.add(Suggestionwords(), "6");
                                        layout.show(mainPanel, "6");
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                        ;
                                    }
                                    return null;
                                }
                            };
                            worker.execute();
                        }else
                            System.out.println(getStatus(msg));
                    }
                }
        );
        JPanel pC2 = new JPanel(new GridBagLayout());
        pC2.add(SG);
        pC.add(pC2);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Welcome!");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);

return c1;
    }

    public JPanel Waiting(){
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JPanel pC = new JPanel(new GridBagLayout());
        JLabel C = J("Waiting for leader ...");
        pC.add(C);



        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Joined game: waiting for leader");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);

return c1;
    }

    public JPanel Suggestionwords(){
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("What is the word for");
        JPanel pC = new JPanel(new BorderLayout());
        JPanel pC1 = new JPanel(new BorderLayout());
        pC1.setBorder(BorderFactory.createTitledBorder(""));
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1,BorderLayout.NORTH);
        pC1.add(createTextArea(10,5,Color.orange,question),BorderLayout.CENTER);

        JPanel pC2 = new JPanel(new FlowLayout());
        pC2.setBorder(BorderFactory.createTitledBorder("Your Suggestion"));
        final JTextField text = createText(10);
        pC2.add(text);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        final JButton SS = new JButton("Submit Suggestion");
        SS.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        SwingWorker worker = new SwingWorker() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                try{
                                    msg = foilmakerController.recieve();
                                    numJoin=0;
                                    int j=0;
                                    while(getMessage(msg,j)!=null){
                                        j++;
                                    }
                                    numJoin=j-3;
                                    System.out.println(numJoin);
                                    options = new String[numJoin+2];
                                    for(int i=0; i<numJoin+2;i++) {
                                        options[i] = getMessage(msg, i+1);
                                    }
                                    mainPanel.add(pickoption(),"7");
                                    layout.show(mainPanel,"7");
                                }catch (IOException e1){
                                    e1.printStackTrace();
                                }
                                return null;
                            }
                        };
                        String suggestion=text.getText();
                        send = "PLAYERSUGGESTION--"+usertoken+"--"+key+"--"+suggestion;
                        try{
                            worker.execute();
                            foilmakerController.send(send);
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }
                        SS.setEnabled(false);


                    }
                }
        );
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SS);
        pC.add(pC3, BorderLayout.SOUTH);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Enter your suggestion");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);
        return c1;
    }

    public JPanel pickoption(){
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("Pick your option below");
        JPanel pC = new JPanel(new BorderLayout());
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC1.setBorder(BorderFactory.createTitledBorder(""));
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1);

        JPanel pC2 = new JPanel(new GridBagLayout());
        pC2.setBorder(BorderFactory.createTitledBorder(""));
        String[] choice = new String[numJoin+2];//random choice
        final JRadioButton[] Choice = new JRadioButton[choice.length];
        for(int i=0;i<numJoin+2;i++)
            choice[i]=options[i];
        ButtonGroup group = new ButtonGroup();
        for(int i=0;i<choice.length;i++){
            Choice[i] = new JRadioButton(choice[i]);
            group.add(Choice[i]);
            pC2.add(Choice[i],s);

        }


        //pC2.add(text);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        final JButton SO = new JButton("Submit Option");
        SO.addActionListener(
                new ActionListener() {
                    String seleted;
                    public void actionPerformed(ActionEvent e){
                        SwingWorker worker = new SwingWorker() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                String msg2 = new String();
                                try {
                                    //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(foilmakerController.socket.getInputStream()));

                                    msg = foilmakerController.recieve();
                                    msg2 = foilmakerController.recieve();
                                    //while(inFromServer==null) {
                                    //msg = inFromServer.readLine();
                                    //msg2 = inFromServer.readLine();

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                String[] Result = new String[numJoin + 1];
                                String[] player = new String[numJoin + 1];
                                String[] Score = new String[numJoin + 1];
                                String[] fool = new String[numJoin + 1];
                                String[] fooled = new String[numJoin + 1];
                                int j = 1;
                                for (int i = 0; i < numJoin + 1; i++) {

                                    player[i] = getMessage(msg, j);
                                    Result[i] = getMessage(msg, j + 1);
                                    Score[i] = getMessage(msg, j + 2);
                                    fool[i] = getMessage(msg, j + 3);
                                    fooled[i] = getMessage(msg, j + 4);
                                    j = j + 5;
                                }
                                int t = 0;
                                while (!player[t].equals(Name))
                                    t++;
                                String overall[] = new String[numJoin + 1];
                                c = createTextArea(12,23,Color.yellow,"");
                                for (int i = 0; i < numJoin + 1; i++) {
                                    overall[i] = player[i] + "==> Score: " + Score[i] + "|Fooled:" + fool[i] + " player(s)|Fooled by: " + fooled[i] + " player(s)";

                                    c.append(overall[i] + "\n");
                                }
                                b =createTextArea(5,23,Color.orange,"");
                                b.append(Result[t]+"\n");

                                mainPanel.add(receiveResults(), "8");
                                layout.show(mainPanel, "8");
                                //System.out.println("mgs2: " + msg2);
                                String a1 = getMessage(msg2, 0);
                                if (a1.equals("NEWGAMEWORD")) {
                                    question = getMessage(msg2, 1);
                                }
                                if (a1.equals("GAMEOVER")) {
                                    mainPanel.add(END(), "9");
                                    layout.show(mainPanel, "9");
                                }

                                return null;
                            }
                        };

                        for(int i=0;i<numJoin+2;i++){
                            if(Choice[i].isSelected()) {
                                seleted = options[i];
                                SO.setEnabled(false);
                            }
                        }
                        send="PLAYERCHOICE--"+usertoken+"--"+key+"--"+seleted;

                        try{worker.execute();
                            foilmakerController.send(send);
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }



                    }
                }
        );
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SO);
        pC.add(pC3, BorderLayout.SOUTH);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Pick your choice");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);
return c1;

    }

    public JPanel receiveResults(){
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JPanel pC = new JPanel(new BorderLayout());
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC1.setBorder(BorderFactory.createTitledBorder("Round Result"));
        pC.setBorder(BorderFactory.createCompoundBorder());



        pC1.add(b);

        JPanel pC2 = new JPanel(new FlowLayout());
        pC2.setBorder(BorderFactory.createTitledBorder("Overall Results"));
        JScrollPane scroll = new JScrollPane(c);
        pC2.add(scroll);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        final JButton SR = new JButton("Next Round");
        SR.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        mainPanel.add(Suggestionwords(),"6");
                        layout.show(mainPanel,"6");



                    }
                }
        );
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SR);
        pC.add(pC3, BorderLayout.SOUTH);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Click <Next Round> when ready");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);
return c1;
    }

    public JPanel END(){
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=0;
        s.weightx=0;
        s.weighty=0;

        this.setTitle("FoilMaker");
        JLabel name = J(Name);//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JPanel pC = new JPanel(new BorderLayout());
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC1.setBorder(BorderFactory.createTitledBorder("Round Result"));
        pC.setBorder(BorderFactory.createCompoundBorder());


        pC1.add(b);

        JPanel pC2 = new JPanel(new FlowLayout());
        pC2.setBorder(BorderFactory.createTitledBorder("Overall Results"));
        JScrollPane scroll = new JScrollPane(c);
        pC2.add(scroll);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        final JButton SR = new JButton("Next Round");
        SR.setEnabled(false);
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SR);
        pC.add(pC3, BorderLayout.SOUTH);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Game over!");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);
        return c1;
    }




//Create Original Text
    public JTextField createText(int a){//a = length
        /*
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createCompoundBorder());
        */
        JTextField input = new JTextField(a);
        return input;
    }

    public JTextArea createTextArea(int a, int b, Color y, String word){
        JTextArea output = new JTextArea(a,b);
        output.setEditable(false);
        output.setBackground(y);
        output.append(word);
        return output;
    }

    //Create Password Text
    public JPasswordField inVisiblecreateText(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createCompoundBorder());
        JPasswordField input = new JPasswordField(10);


        return input;
    }
//for input name variable.
    public JLabel J(String name){
        JLabel J = new JLabel(name);
        return J;
    }

    public String getStatus(String message){
        String msg =message;
        int l = msg.length();
        int a=msg.indexOf("--");
        String msg1 =msg.substring(a+2,l);
        l = msg1.length();
        a =msg1.indexOf("--");
        String msg2 = msg1.substring(a,l);
        l = msg2.length();
        String msg3 = msg2.substring(0+2,l);
        a=msg3.indexOf("--");
        String status = msg3.substring(0,a);
        return status;

    }

    public String getMessage(String message, int i){
        for(int j=0;j<i;j++) {
            int l = message.length();
            int a = message.indexOf("-");
            if(a==-1)
                return null;
            message = message.substring(a + 2, l);
        }
        int a = message.indexOf("-");
        if(a==-1)
            return message;
        else
            message = message.substring(0,a);
        return message;


    }



    public void actionPerformed(ActionEvent e){






    }

}


