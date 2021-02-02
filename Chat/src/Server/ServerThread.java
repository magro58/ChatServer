
package Server;

import java.io.*;
import java.net.*;

/**
 * @author Magrini
 */
public class ServerThread extends Thread
{
       public BufferedReader input;
       public DataOutputStream output;
       public Socket sock;
       public String username;
       public String msg;
       public int pt;
       public String wh;
       public MainServer server;
        
        public ServerThread(Socket sock, MainServer server)
        {
            this.sock=sock;
            this.server=server;
        }

        public void run()
        {
            try
            {
                input= new BufferedReader(new InputStreamReader(sock.getInputStream()));
                output= new DataOutputStream(sock.getOutputStream());

                output.writeBytes("Inserisci il tuo username: \n");
                username=input.readLine();
                server.sendMsg(username+" Connesso", "Server");
                for(;;)
                {
                    msg=input.readLine();
                    pt=msg.indexOf("@");
                    if(msg.equalsIgnoreCase("exit"))
                    {
                        server.sendMsg(username+" disconnesso.", "Server");
                        output.writeBytes("Fine del servizio. \n");
                        break;
                    }
                    else if(pt == 0 && !msg.equalsIgnoreCase("@ls"))
                    {
                        pt=msg.indexOf(" ");
                        wh=msg.substring(1, pt);
                        System.out.println(wh);
                        server.Wh(msg, wh, this.username);
                    }
                    else if(msg.equals("@ls"))
                    {
                        server.List(username);
                    }
                    else
                    {
                        server.sendMsg(msg, username);
                    }
                }
                server.rm(this.username);
                this.stop();
                System.exit(0);
            }
            catch(IOException e)
            {
                System.out.println("Errore nell'invio del messaggio... \n");
            }
        }

        public String getUsername()
        {
            return this.username;
        }
}