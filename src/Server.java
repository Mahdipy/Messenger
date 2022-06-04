import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<ServerController> ar = new ArrayList<>();
    static int i = 0;

    public static void main(String[] args)throws Exception {

        // server is listening on port 1234
        ServerSocket doorbellSocket = new ServerSocket(8790);
        Filework filework = new Filework();
        Socket s;
        ArrayList client = new ArrayList();
        client = filework.userSearch();
        for (int i=0;i<client.size();i++){
            System.out.println(client.get(i));
        }
        while (true)
        {
            // Accept the incoming request
            s = doorbellSocket.accept();
            System.out.println("We have a new Client\n"+s);
            // obtain input and output streams
            DataInputStream inFromClient = new DataInputStream(s.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());

            // Create a new handler object for handling this request.
            String name=inFromClient.readUTF();
            System.out.println(name+" Joined!");
            ServerController mtch = new ServerController(s,name, inFromClient, outToClient,true);
            // Create a new Thread with this object.
            Thread t = new Thread(mtch);
            // add this client to active clients list
            ar.add(mtch);
            // start the thread.
            t.start();
            i++;
        }
    }
}
