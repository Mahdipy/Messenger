import java.io.*;
import java.util.ArrayList;

public class Filework implements Runnable{
    String user;
    String pass;
    String path;
    public Filework(String user , String pass) {
        this.pass = pass;
        this.user = user;
    }

    public Filework(String user , String pass,String path) {
        this.path = path;
        this.pass = pass;
        this.user = user;
    }
    public Filework(){}


    //write user and pass into file
    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("mahdi.txt", true));
            out.newLine();
            out.write(user);
            out.newLine();
            out.write(pass);

            out.close();
        }
        catch (Exception e){

        }
       }

    //write path into file
       public void addImage(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("image.txt", true));
            out.write(path);
            out.newLine();
            out.close();
        }
        catch (Exception e){System.out.println("Fuck");}
       }

       //read path from file
       public String getImage(int n){
           String p = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("image.txt"));

            for (int i = 0; i <= n; i++) {
                p = in.readLine();
            }
            in.close();
        }
        catch (Exception e){
            System.out.println("Sheet");
        }
        return p;
       }
       //if the username and pass correct
    public boolean search() throws IOException{
        boolean a;
        BufferedReader in = new BufferedReader(new FileReader("mahdi.txt"));
        String username;
        String password;
        int i=0;
        while ((username=in.readLine())!=null){
            password = in.readLine();
            if (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass))
                i=1;
        }
        if (i==0) a=false;
        else a=true;
        in.close();
        return a;
    }


    //return all users
    public ArrayList userSearch()throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("mahdi.txt"));
        ArrayList<String> username = new ArrayList<String>();
        int i=0;
        String name;
        while ((name=in.readLine())!=null){
            in.readLine();
            username.add(name);
            i++;
        }
            return username;
        }
}
