import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class Main implements ActionListener
{
    private static JLabel userLabel;
    private static JButton adminCreate;
    private static JLabel adminWords;
    private static JLabel passLabel;
    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton button;
    private static JButton logBut;
    private static JLabel success;
    private static JButton createBut;
    private static JLabel header;
    private static JPanel panel;
    private static JLabel message;
    private static ArrayList<String> UserList;
    private static ArrayList<String> PassList;
    private static ArrayList<String> adminUList;
    private static ArrayList<String> adminPList;
    private static JFrame frame;
    private static boolean visible;
    private static JButton logIn;
    private static JButton adminBut;
    private static JButton userBut;
    private static boolean log;
    private static boolean create;
   // private static ArrayList<String> passList;
    private static JLabel userHeader;
    private static JLabel adminHeader;
    private static JButton yesBut;
    private static JButton noBut;
    private static JLabel question;
    private static int numYes;
    private static int numNo;
    private static JLabel endMess;
    private static JLabel endMess2;
    private static String salt;
    private static JButton logOut;
    private static JLabel number;
    private static JButton adminLog;
    private static int accounts;
    //create a new log in button for Admin
    public static void main(String[] args) throws IOException
    {
        salt = "mjallday321";
        Hashing hashing = new Hashing();
        //System.out.ku6543211  b,.println(hashing.hash(hashing.hash("michael"+salt)));
        //userInFile(hashing.hash(hashing.hash("michael"+salt)));
        //passInFile(hashing.hash(hashing.hash("michael"+salt)));
        visible = false;
        UserList = new ArrayList<String>();
        PassList = new ArrayList<String>();
        adminUList = new ArrayList<String>();
        adminPList = new ArrayList<String>();
        loadAccounts();
        loadAdminAccounts();
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        header = new JLabel("Welcome to Allers Inc.");
        header.setBounds(110,5,170,25);
        panel.add(header);
        panel.setLayout(null);
        
        message = new JLabel("");
        message.setBounds(105,90,250,25);
        panel.add(message);
        
        userLabel = new JLabel("Username");
        userLabel.setBounds(15,40,80,25);
        panel.add(userLabel);
        userLabel.setVisible(false);  
         
        passLabel = new JLabel("Password");
        passLabel.setBounds(15,70,80,25);
        panel.add(passLabel);
        passLabel.setVisible(false);
        
        
        userText = new JTextField(20);
        userText.setBounds(100,40,165,25);
        panel.add(userText);
        userText.setVisible(false);
        
        passText = new JPasswordField(20);
        passText.setBounds(100,70,165,25);
        panel.add(passText);
        passText.setVisible(false);
        
        adminCreate = new JButton("Create Account");
        adminCreate.setBounds(120,140,125,25);
        adminCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent m){
             try{
                    String user = userText.getText();
                    String pass = passText.getText();
                    
                    if(pass.length()<15){
                        message.setText("Password must be 15+ characters");
                        message.setBounds(105,90,250,25);
                    }else if(!containsNum(pass)){
                            message.setText("Password must contain a number");
                            message.setBounds(105,90,250,25);
                        }else if(!containsSpec(pass)){
                        message.setText("Password must contain special character");
                        message.setBounds(85,90,275,25);
                    }else if(user.length()<1){
                        message.setText("Username must be longer");
                        message.setBounds(105,90,250,25);
                    }else if(userAdminExists(hashing.hash(hashing.hash(user+salt))) == true || AdminAndUser(hashing.hash((user+salt)))){
                        message.setText("Username not valid");
                    }else{
                        //message.setText("Done");
                        //userAdminInFile(hashing.hash(user+salt));
                        //passAdminInFile(hashing.hash(pass+salt));
                        writeAdmin(hashing.hash(hashing.hash(user+salt)),(hashing.hash(hashing.hash(pass+salt))));
                        adminUList.add(hashing.hash(hashing.hash(user+salt)));
                        adminPList.add(hashing.hash(hashing.hash(pass+salt)));
                        button.setVisible(false);
                        passText.setVisible(false);
                        userText.setVisible(false);
                        adminCreate.setVisible(false);
                        passLabel.setVisible(false);
                        userLabel.setVisible(false);
                        header.setVisible(false);
                        message.setText("");
                        //yesBut.setVisible(true);
                        //noBut.setVisible(true);
                        adminLog.setVisible(false);
                        passText.setVisible(false);
                        userText.setVisible(false);
                        passLabel.setVisible(false);
                        userLabel.setVisible(false);
                        header.setVisible(false);
                        message.setText("");
                        question.setVisible(false);
                        adminHeader.setVisible(true);
                        accounts = numAccounts();
                        adminWords.setText("Number of accounts: "+String.valueOf(UserList.size()));
                        adminWords.setVisible(true);
                        logOut.setVisible(true);
                        number.setVisible(true);
                        createBut.setVisible(false);
                        logOut.setVisible(true);
                    }
                    
            
                    }catch (Exception e){
                        System.out.println("Error");
                    }
            }
        });
        panel.add(adminCreate);
        adminCreate.setVisible(false);
        //buttons
        button = new JButton("Create Account");
        button.setBounds(120,115,125,25);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
                try{
                String user = userText.getText();
                String pass = passText.getText();
                //String specChar=" !#$%&'()*+,-./:;<=>?@[]^_`{|}";
                //String nums = "1234567890";
                //System.out.println(pass);
                
            
                if(pass.length()<10){
                    message.setText("Password must be 10+ characters");
                    message.setBounds(105,90,250,25);
                }else if(!containsNum(pass)){
                        message.setText("Password must contain a number");
                        message.setBounds(105,90,250,25);
                    }else if(!containsSpec(pass)){
                    message.setText("Password must contain special character");
                    message.setBounds(85,90,275,25);
                }else if(user.length()<1){
                    message.setText("Username must be longer");
                    message.setBounds(105,90,250,25);
                }else if((userExists(hashing.hash(user+salt)))){
                    message.setText("Username not valid");
                }else {
                    //message.setText("Done");
                    UserList.add(hashing.hash(user+salt));
                    PassList.add(hashing.hash(pass+salt));
                    writeAccounts(hashing.hash(user+salt),hashing.hash(pass+salt));
                    //userInFile(hashing.hash(user+salt));
                    //passInFile(hashing.hash(pass+salt));
                    button.setVisible(false);
                    passText.setVisible(false);
                    userText.setVisible(false);
                    passLabel.setVisible(false);
                    userLabel.setVisible(false);
                    header.setVisible(false);
                    message.setText("");
                    //yesBut.setVisible(true);
                    //noBut.setVisible(true);
                    question.setVisible(true);
                    userHeader.setVisible(true);
                    logOut.setVisible(true);
                }
            
            }catch (Exception e){
                System.out.println("Error");
            }
            }
        }); 
        panel.add(button);
        button.setVisible(false);
        
        logIn = new JButton("Login");
        logIn.setBounds(120,120,125,25);
        logIn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent b){
                String user = userText.getText();
                String pass = passText.getText();  
                try{
                    if(userFound(hashing.hash(user+salt)) == true && passwFound(hashing.hash(pass+salt)) == true){
                        //message.setText("Login successful");
                        logIn.setVisible(false);
                        passText.setVisible(false);
                        userText.setVisible(false);
                        passLabel.setVisible(false);
                        userLabel.setVisible(false);
                        header.setVisible(false);
                        message.setText("");
                        
                        //yesBut.setVisible(true);
                        //noBut.setVisible(true);
                        question.setVisible(true);
                        userHeader.setVisible(true);
                        logOut.setVisible(true);
                        createBut.setVisible(false);
                    } else {
                        message.setText("Account not found");
                    }
                }catch(Exception e){
                    System.out.println("error");
                }
                
            }
        }
            );
        panel.add(logIn);
        logIn.setVisible(false);
        
        logBut = new JButton("Login");
        logBut.setBounds(120,50,125,25);
        logBut.addActionListener(new Main());
        panel.add(logBut);
        logBut.setVisible(false);
        
        createBut = new JButton("Create Account");
        createBut.setBounds(120,90,125,25);
        createBut.addActionListener(new Main());
        panel.add(createBut);
        createBut.setVisible(false);
        
        adminBut = new JButton("Admin");
        adminBut.setBounds(120,50,125,25);
        adminBut.addActionListener(new Main());
        panel.add(adminBut);
        //adminBut.setVisible(false);
        
        userBut = new JButton("User");
        userBut.setBounds(120,90,125,25);
        userBut.addActionListener(new Main());
        panel.add(userBut);
        //userBut.setVisible(false);
        adminWords = new JLabel("Number of accounts: ");
        adminWords.setBounds(100,50,180,25);
        panel.add(adminWords);
        adminWords.setVisible(false);
        
        number = new JLabel("");
        number.setBounds(240,50,180,25);
        panel.add(number);
        number.setVisible(false);
       
        //after user logs in
        userHeader = new JLabel("Hello User!");
        userHeader.setBounds(145,5,170,25);
        panel.add(userHeader);
        userHeader.setVisible(false);
        
        adminHeader = new JLabel("Hello Admin!");
        adminHeader.setBounds(135,5,170,25);
        panel.add(adminHeader);
        adminHeader.setVisible(false);
        
        question = new JLabel("Is a hotdog a sandwhich?");
        question.setBounds(90,60,180,25);
        panel.add(question);
        question.setVisible(false);
        
        yesBut = new JButton("Yes");
        yesBut.setBounds(60,75,125,25);
        yesBut.addActionListener(new Main());
        panel.add(yesBut);
        yesBut.setVisible(false);
        
        noBut = new JButton("No");
        noBut.setBounds(180,75,125,25);
        noBut.addActionListener(new Main());
        panel.add(noBut);
        noBut.setVisible(false);
        
        endMess = new JLabel("Thank you for your response");
        endMess.setBounds(85,25,200,60);
        panel.add(endMess);
        endMess.setVisible(false);
        
        endMess2 = new JLabel("Have a nice day :)");
        endMess2.setBounds(120,45,180,60);
        panel.add(endMess2);
        endMess2.setVisible(false);
        
        logOut = new JButton("Log Out");
        logOut.setBounds(120,120,110,25);
        logOut.addActionListener(new Main());
        panel.add(logOut);
        logOut.setVisible(false);
        
        adminLog = new JButton("Login");
        adminLog.setBounds(128,110,110,25);
        adminLog.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                String user = userText.getText();
                String pass = passText.getText();  
                try{
                    if(adminUser(hashing.hash(hashing.hash(user+salt))) == true && adminPass(hashing.hash(hashing.hash(pass+salt))) == true){
                        //message.setText("Login successful");
                        adminLog.setVisible(false);
                        passText.setVisible(false);
                        userText.setVisible(false);
                        passLabel.setVisible(false);
                        userLabel.setVisible(false);
                        header.setVisible(false);
                        message.setText("");
                        //yesBut.setVisible(true);
                        //noBut.setVisible(true);
                        question.setVisible(false);
                        adminHeader.setVisible(true);
                        accounts = numAccounts();
                        adminWords.setText("Number of accounts: "+UserList.size());
                        adminWords.setVisible(true);
                        logOut.setVisible(true);
                        number.setVisible(true);
                        createBut.setVisible(false);
                        adminCreate.setVisible(false);
                    } else {
                        message.setText("Account not found");
                    }
                }catch(Exception e){
                    System.out.println("error");
                }
                
            }
        }
        );
        panel.add(adminLog);
        adminLog.setVisible(false);
        //panel.setLayout(null);
        frame.setVisible(true);
    }
    //cant throw IOException in actionPerformed
    public void actionPerformed(ActionEvent e) {
        /*if(e.getSource() == button){
            String user = userText.getText();
            String pass = passText.getText();
            //String specChar=" !#$%&'()*+,-./:;<=>?@[]^_`{|}";
            //String nums = "1234567890";
            System.out.println(pass);
                
            
            if(pass.length()<10 || !containsNum(pass) || !containsSpec(pass)){
                message.setText("Password critera not met");
            }else {
                UserList.add(user);
                PassList.add(pass);
                //userInFile(user);
                //passInFile(pass);
            }
            
        }*/
        if(e.getSource() == yesBut){
            yesBut.setVisible(false);
            noBut.setVisible(false);
            question.setVisible(false);
            userHeader.setVisible(false);
            
            endMess.setVisible(true);
            endMess2.setVisible(true);
        }
        if(e.getSource() == noBut){
            yesBut.setVisible(false);
            noBut.setVisible(false);
            question.setVisible(false);
            userHeader.setVisible(false);
            
            endMess.setVisible(true);
            endMess2.setVisible(true);
        }
        if(e.getSource() == logBut){
          //System.out.println("login button");
          userLabel.setVisible(true);
          passLabel.setVisible(true);
          userText.setVisible(true);
          passText.setVisible(true);
          userBut.setVisible(false);
          //adminBut.setVisible(false);
          //panel.add(userLa
          //panel.add(passLabel);
          //panel.add(userText);
          //panel.add(passText);
          //adminBut.setVisible(true);
          //userBut.setVisible(true);
          logBut.setVisible(false);
          createBut.setVisible(true);
          createBut.setBounds(120,150,125,25);
          logIn.setVisible(true);
          adminBut.setVisible(false);
          //log = true;
          
        }
        if(e.getSource() == createBut){
          //System.out.println("create account option");
          userLabel.setVisible(true);
          passLabel.setVisible(true);
          userText.setVisible(true);
          passText.setVisible(true);
          //userBut.setVisible(false);
          //adminBut.setVisible(false);
          //adminBut.setVisible(true);
          //userBut.setVisible(true);
          //logBut.setVisible(false);
          createBut.setVisible(false);
          logBut.setVisible(false);
          button.setVisible(true);
          logIn.setVisible(false);
          adminBut.setVisible(false);
          message.setText("");
          //panel.remove(logBut);
        }
        if(e.getSource() == adminBut){
          userLabel.setVisible(true);
          passLabel.setVisible(true);
          userText.setVisible(true);
          passText.setVisible(true);
          userBut.setVisible(false);
          adminBut.setVisible(false);
          //button.setVisible(true);
          adminLog.setVisible(true);
          adminCreate.setVisible(true);
        }
            
        if(e.getSource() == userBut){
          createBut.setVisible(true);
          createBut.setBounds(120,90,125,25);
          logBut.setVisible(true);
          
          //userLabel.setVisible(true);
          //passLabel.setVisible(true);
          //userText.setVisible(true);
          //passText.setVisible(true);
          //adminBut.setVisible(false);
          userBut.setVisible(false);
        }
        if(e.getSource() == logOut){
            userHeader.setVisible(false);
            yesBut.setVisible(false);
            noBut.setVisible(false);
            question.setVisible(false);
            logOut.setVisible(false);
            
            header.setVisible(true);
            adminBut.setVisible(true);
            userBut.setVisible(true);
            adminHeader.setVisible(false);
            number.setVisible(false);
            adminWords.setVisible(false);
            
            
        }
        
            
        
        //if(user.equals() && userPass.hash =
    }
    
    public static boolean containsNum(String inp){
        //System.out.println(inp);
        for(int i = 0; i<inp.length(); i++){
            if(Character.isDigit(inp.charAt(i))){
                //System.out.println(inp.charAt(i));
                return true;
            }
        }
        return false;
    }
    
    public static boolean AdminAndUser(String username){
        for(int i = 0; i<UserList.size(); i++){
            if(UserList.get(i).equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsSpec(String inp){
        //https://www.geeksforgeeks.org/java-program-to
        //-check-whether-the-string-consists-of-special-characters/
        // Creating regex pattern by
        // creating object of Pattern class
        Pattern p = Pattern.compile(
            "[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
  
        // Creating matcher for above pattern on our string
        Matcher m = p.matcher(inp);
  
        // Now finding the matches for which
        // let us set a boolean flag and
        // imposing find() method
        boolean res = m.find();
        return res;
    }
    public static boolean userFound(String user) {
       for(int i = 0; i<UserList.size(); i++){
            if(UserList.get(i).equals(user)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean passwFound(String inp){
        for(int i = 0; i<PassList.size(); i++){
            if(PassList.get(i).equals(inp)){
                return true;
            }
        }
        return false;
    }
    
    public static void writeAccounts(String username, String password) throws IOException{
        FileWriter writer = new FileWriter("UserAccounts.txt", true);
        writer.write(username+","+password+"\n");
        writer.close();
        
    }
    
    public static void writeAdmin(String username, String password) throws IOException{
        FileWriter writer = new FileWriter("AdminAccounts.txt", true);
        writer.write(username+","+password+"\n");
        writer.close();
        
    }
    
    public static boolean inputMatch(String password, String username){
        int passIndex = 0;
        int userIndex = 0;
        for(int i = 0; i<PassList.size(); i++){
            if(PassList.get(i).equals(password)){
                passIndex = i;
            }
        }
        
        for(int i = 0; i<UserList.size(); i++){
            if(UserList.get(i).equals(username)){
                userIndex = i;
            }
        }
        
        if(passIndex == userIndex){
            return true;
        }else {
            return false;
        }
             
    }
    public static int numAccounts(){
        return UserList.size();
    }
    public static boolean adminPass(String inp){
        for(int i = 0; i<adminPList.size(); i++){
            if(adminPList.get(i).equals(inp)){
                return true;
            }
        }
        return false;
    }
    public static boolean adminUser(String inp){
        for(int i = 0; i<adminUList.size(); i++){
            if(adminUList.get(i).equals(inp)){
                return true;
            }
        }
        return false;
    }
    public static boolean adminMatch(String password, String username) {
        int passIndex = 0;
        int userIndex = 0;
        for(int i = 0; i<adminPList.size(); i++){
            if(adminPList.get(i).equals(password)){
                passIndex = i;
            }
        }
        
        for(int i = 0; i<adminUList.size(); i++){
            if(adminUList.get(i).equals(username)){
                userIndex = i;
            }
        }
        
        if(passIndex == userIndex){
            return true;
        }else {
            return false;
        }
               
                
    }
    public static boolean userExists(String user){
        for(int i = 0; i<UserList.size(); i++){
            if(UserList.get(i).equals(user)){
                return true;
            }
        }
        return false;
    }
    public static boolean userAdminExists(String user) {
        for(int i = 0; i<adminUList.size(); i++){
            if(adminUList.get(i).equals(user)){
                return true;
            }
        }
        return false;
    }
    public static void loadAccounts() throws IOException{
        FileReader reader = new FileReader("UserAccounts.txt");
        BufferedReader br = new BufferedReader(reader);
        String info;
        String i = br.readLine();
        while(i != null){
            info = i;
            System.out.println(i);
            String acc[] = info.split(",");
            String username = acc[0];
            UserList.add(username);
            String password = acc[1];
            PassList.add(password);
            i = br.readLine();
        }
        br.close();
        reader.close();
    }
    
    public static void loadAdminAccounts() throws IOException{
        FileReader reader = new FileReader("AdminAccounts.txt");
        BufferedReader br = new BufferedReader(reader);
        String info;
        String i = br.readLine();
        while(i != null){
            info = i;
            String acc[] = info.split(",");
            String username = acc[0];
            adminUList.add(username);
            String password = acc[1];
            adminPList.add(password);
            i = br.readLine();
        }
        br.close();
        reader.close();
    }
}
