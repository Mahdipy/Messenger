import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerController implements Runnable
{
    private String name;
    final DataInputStream inFromClient;
    final DataOutputStream outToClient;
    Socket s;
    boolean isloggedin;
    // constructor
    public ServerController(Socket s, String name, DataInputStream inFromClient, DataOutputStream outToClient , boolean isloggedin) {
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
        this.name = name;
        this.s = s;
        this.isloggedin=isloggedin;

    }
    boolean online;
    boolean a;
    @Override
    public void run() {
        String received;
        while (true)
        {
            try
            {
                // receive the string
                received = inFromClient.readUTF();

                System.out.println(received);


                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String sender = st.nextToken();
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();
                online=true;
                if(MsgToSend.equals("logout")){
                    this.isloggedin=false;
                    online = false;
                }

                for (ServerController mc : Server.ar)
                {
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin && online)
                    {
                        mc.outToClient.writeUTF(sender);
                        mc.outToClient.writeUTF(MsgToSend);
                        a=true;
                        break;
                    }
                    else if(!online) {
                        for (int i=0 ; i<Server.ar.size() ; i++){
                            Server.ar.get(i).outToClient.writeUTF(sender);
                            Server.ar.get(i).outToClient.writeUTF(MsgToSend);
                        }
                        a=false;
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            if (!a) {
                try{
                this.s.close();
                break;}catch (Exception e){}
            }
        }
        try
        {
            // closing resources
            this.inFromClient.close();
            this.outToClient.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}