import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
public class Client
{
    First first ;
    String name;
    Stage primaryStage;
    public Client(String name , Stage primaryStage) {
        this.name = name;
        this.primaryStage = primaryStage;
        primaryStage.setTitle(name);
        first = new First();
        first.run(primaryStage);
    }

    public  void run() throws  IOException
    {
        // establish the connection
        Socket s = new Socket("localhost", 8790);

        // obtaining input and out streams
        DataInputStream inFromServer = new DataInputStream(s.getInputStream());
        DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());
        try {
            outToServer.writeUTF(name);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    for (int i = 0; i<first.profiles.size() ; i++) {
                        int finalI = i;
                    first.profiles.get(i).button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            // read the message to deliver.
                            String msg = first.profiles.get(finalI).textField.getText();
                            if (!msg.equals("")){
                                first.profiles.get(finalI).add(msg,false);
                                try {
                                    // write on the output stream
                                    outToServer.writeUTF(name+"#"+msg+"#"+first.profiles.get(finalI).name);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }}
                    });
                    first.profiles.get(i).textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                        @Override
                                        public void handle(KeyEvent event) {
                                            if (event.getCode()== KeyCode.ENTER){
                                                // read the message to deliver.
                                                String msg = first.profiles.get(finalI).textField.getText();
                                              if (!msg.equals("")){
                                                first.profiles.get(finalI).add(msg,false);
                                                try {
                                                    // write on the output stream
                                                    outToServer.writeUTF(name+"#"+msg+"#"+first.profiles.get(finalI).name);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                }
                            }}
                        }
                    });
                    first.logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                outToServer.writeUTF(name+"#"+"logout"+"#"+name);
                                primaryStage.close();
                                System.exit(0);
                            }catch (Exception e){}
                        }
                    });
                }}
            }
        });

        // readMessage thread
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {

                    try {

                        // read the message sent to this client
                        String sender = inFromServer.readUTF();
                        String msg = inFromServer.readUTF();
                        for (int i = 0; i<first.profiles.size() ; i++) {
                            if (sender.equals(first.profiles.get(i).name)){
                                if (msg.equals("logout"))first.profiles.get(i).online.setText("Offline");
                                else first.profiles.get(i).add(msg, true);
                            }

                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                        e.getMessage();
                    }

                }
            }
        });

        sendMessage.start();
        readMessage.start();
    }
}
