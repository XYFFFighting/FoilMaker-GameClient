import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yufei Xu on 10/22/2016.
 * implements Java GUI classes
 * program out put
 */
public class foilmakerView extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();


public void run(){
    mainPanel.setLayout(layout);
    mainPanel.add(Login(),"1");
    mainPanel.add(Login2(),"2");
    mainPanel.add(StartnewGame(),"3");
    mainPanel.add(JoinGame(),"4");

    add(mainPanel);
    setLocation(300,500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    layout.show(mainPanel,"1");
    setVisible(true);


}


//LOGIN GUI
    public JPanel Login(){
        JButton loginButton, Register;
        JLabel Username, Password, foilMaker;

        this.setTitle("FoilMaker!");

        Register = new JButton("Register");
        Register.addActionListener(this);
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

        JPanel login = new JPanel(new FlowLayout());
        login.add(Username);
        final JTextField input = new JTextField(10);
        JPanel t1 = new JPanel(new GridBagLayout());
        t1.add(input);
        login.add(t1);


        JPanel login3 = new JPanel(new FlowLayout());
        login3.add(Password);
        login3.add(inVisiblecreateText());

        JPanel login2 = new JPanel(new GridLayout(0,1));

        login2.setBorder(BorderFactory.createTitledBorder(""));
        login2.add(login);
        login2.add(login3);
        loginButton = new JButton("Login");
        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String name = input.getText();
                        System.out.println(name+" is logging in");
                        layout.show(mainPanel,"2");
                        
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
        JLabel Newusercreated = new JLabel("New user created");
        pS.add(Newusercreated);

        c1.add(pN,BorderLayout.NORTH);
        c1.add(panel, BorderLayout.CENTER);
        c1.add(pS, BorderLayout.SOUTH);

return c1;
    }

    public JPanel Login2(){
        this.setTitle("FoilMaker");
        JLabel name = J("BOb");//"Bob will come from Controller. J is a method
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JButton SNG = new JButton("Start New Game");
        SNG.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        layout.show(mainPanel,"3");

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
        JLabel name = J("Bob");//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("Others should use this key to join your game");
        JPanel pC = new JPanel(new GridLayout(0,1));
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1, s);

        pC1.add(createText(4));
        pC.add(pC1);
        JButton SG = new JButton("Start Game");
        JPanel participants = new JPanel(new BorderLayout());
        participants.setBorder(BorderFactory.createTitledBorder("Participants"));
        participants.add(createTextArea(5,1, Color.YELLOW));
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
        JLabel name = J("Bob");//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JLabel S1 = J("Enter the game key to join a game");
        JPanel pC = new JPanel(new GridLayout(0,1));
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC.setBorder(BorderFactory.createCompoundBorder());

        pC1.add(S1, s);

        pC1.add(createText(4));
        pC.add(pC1);

        JButton SG = new JButton("Join Game");
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
        JLabel name = J("Bob");//"Bob" will come from Controller
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
        JLabel name = J("Bob");//"Bob" will come from Controller
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
        pC1.add(createTextArea(10,5,Color.orange),BorderLayout.CENTER);

        JPanel pC2 = new JPanel(new FlowLayout());
        pC2.setBorder(BorderFactory.createTitledBorder("Your Suggestion"));
        JPanel text = createText(10);
        pC2.add(text);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        JButton SG = new JButton("Submit Suggestion");
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SG);
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
        JLabel name = J("Bob");//"Bob" will come from Controller
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
        String[] choice = {"1","2","3"};//random choice
        JRadioButton[] Choice = new JRadioButton[choice.length];
        for(int i=0;i<choice.length;i++){
            Choice[i] = new JRadioButton(choice[i]);
            pC2.add(Choice[i],s);

        }


        //pC2.add(text);

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        JButton SG = new JButton("Submit Option");
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SG);
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
        JLabel name = J("Bob");//"Bob" will come from Controller
        JPanel c1 = new JPanel();
        c1.setLayout(new BorderLayout());

        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JPanel pC = new JPanel(new BorderLayout());
        JPanel pC1 = new JPanel(new GridBagLayout());
        pC1.setBorder(BorderFactory.createTitledBorder("Round Result"));
        pC.setBorder(BorderFactory.createCompoundBorder());


        pC1.add(createTextArea(5,23,Color.orange));

        JPanel pC2 = new JPanel(new FlowLayout());
        pC2.setBorder(BorderFactory.createTitledBorder("Overall Results"));
        pC2.add(createTextArea(15,23,Color.yellow));

        pC.add(pC1, BorderLayout.NORTH);
        pC.add(pC2,BorderLayout.CENTER);

        JButton SG = new JButton("Next Round");
        JPanel pC3 = new JPanel(new GridBagLayout());
        pC3.add(SG);
        pC.add(pC3, BorderLayout.SOUTH);

        JPanel pS = new JPanel(new GridLayout(0,1));
        JLabel GS = J("Click <Next Round> when ready");
        pS.add(GS);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS,BorderLayout.SOUTH);
return c1;
    }




//Create Original Text
    public JPanel createText(int a){//a = length
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createCompoundBorder());
        JTextField input = new JTextField(a);
        panel.add(input);
        return panel;
    }

    public JPanel createTextArea(int a, int b, Color y){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        JTextArea output = new JTextArea(a,b);
        output.setEditable(false);
        output.setBackground(y);
        panel.add(output);
        return panel;
    }

    //Create Password Text
    public JPanel inVisiblecreateText(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createCompoundBorder());
        JPasswordField input = new JPasswordField(10);


        panel.add(input);
        return panel;
    }
//for input name variable.
    public JLabel J(String name){
        JLabel J = new JLabel(name);
        return J;
    }


    public void actionPerformed(ActionEvent e){






    }

}


