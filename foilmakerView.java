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




    public static void main(String[] args) {
        foilmakerView f = new foilmakerView();
//test GUI
        f.setVisible(true);
        //f.Login();
        //f.pack();
        f.Login2();
        f.pack();

    }
//LOGIN GUI
    public void Login(){
        JButton loginButton, Register;
        JLabel Username, Password, foilMaker;

        this.setTitle("FoilMaker!");
        loginButton = new JButton("Login");
        Register = new JButton("Register");
        Username = new JLabel("Username");
        Password = new JLabel("Password");
        foilMaker = J("FoilMaker!");



        Container c1 = this.getContentPane();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel pN = new JPanel(new FlowLayout());
        pN.add(foilMaker);


        JPanel pC = new JPanel(new BorderLayout());
        //pC.setBorder(BorderFactory.createTitledBorder(""));

        JPanel login = new JPanel(new FlowLayout());
        login.add(Username);
        login.add(createText());
        JPanel login3 = new JPanel(new FlowLayout());
        login3.add(Password);
        login3.add(inVisiblecreateText());

        JPanel login2 = new JPanel(new GridLayout(0,1));
        login2.setBorder(BorderFactory.createTitledBorder(""));
        login2.add(login);
        login2.add(login3);


        JPanel loginbutton = new JPanel(new FlowLayout());
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

    }

    public void Login2(){
        JLabel name = J("BOb");//"Bob will come from Controller.
        Container c1 = this.getContentPane();
        c1.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel pN = new JPanel(new FlowLayout());
        pN.add(name);

        JButton SNG = new JButton("Start New Game");
        JButton JaG = new JButton("Join a Game");

        JPanel pC = new JPanel(new FlowLayout());
        pC.add(SNG);
        pC.add(JaG);

        JPanel pS = new JPanel(new GridLayout(0,3));
        JLabel Welcom = J("Welcome!");
        pS.add(Welcom);

        c1.add(pN, BorderLayout.NORTH);
        c1.add(pC, BorderLayout.CENTER);
        c1.add(pS, BorderLayout.SOUTH);





    }
//Create Original Text
    public JPanel createText(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createCompoundBorder());
        JTextField input = new JTextField(10);
        panel.add(input);
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


