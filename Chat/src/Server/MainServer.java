
package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Magrini
 */
public class MainServer 
{
   public ServerSocket socket;
   public ArrayList <ServerThread> lista = new ArrayList <>();
   public BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) 
    {
        MainServer server = new MainServer();
        server.connect();
    }

    public void connect()
    {
        try
        {
            socket= new ServerSocket(4567);
            System.out.println("Server partito... \n");
            for(int i=0; i<64; i++) 
            {
                Socket accept = socket.accept();
                ServerThread st =new ServerThread(accept, this);
                lista.add(st);
                System.out.println("Client connesso, spazi rimanenti: "+(64-(i+1))+" \n");
                lista.get(i).start();
            }
            socket.close();       
        }
        catch(Exception e)
        {
            System.out.println("Errore di connessione con il client \n");
        }
    }

    public void sendMsg(String msg, String username)
    {
        for(ServerThread s: lista)
        {
            try 
            {
                s.output.writeBytes(username +": "+ msg +"\n");
            } 
            catch (IOException e) 
            {
                System.out.println("Errore nell'invio del messaggio \n");
            }
        }
    }
    
    public void Wh(String msg, String username, String sender)
    {
        for(ServerThread ser: lista)
        {
            if(username.equalsIgnoreCase(ser.getUsername()))
            {
                try 
                {
                    ser.output.writeBytes("(Whisper)"+sender+": "+msg+"\n");
                    break;
                } 
                catch (IOException e) 
                {
                    System.out.println("Errore nell'invio del whisper \n");
                }
            }
        }
    }
    
    public void rm(String username)
    {
        for(ServerThread server : lista)
        {
            if(username.equalsIgnoreCase(server.getUsername()))
            {
                try
                {
                    for(ServerThread x: lista)
                    {
                        lista.remove(x.getUsername());
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Errore nell'eliminazione del client dalla lista dei membri...\n");
                }
            }
        }
    }

    public void List(String username)
    {
        ArrayList <String> usernames=new ArrayList<>();
        for(ServerThread s: lista)
        {
            if(username.equalsIgnoreCase(s.getUsername()))
            {
                try 
                {
                    for(ServerThread x: lista)
                    {
                        usernames.add(x.getUsername());
                    }
                    System.out.println("Membri presenti: \n");
                    for(int i=0;i<lista.size();i++)
                    {
                        s.output.writeBytes(usernames.get(i)+"\n");
                    }
                } 
                catch (IOException e) 
                {
                    System.out.println("Errore nella presentazione della lista dei membri... \n");
                }
            }
        }
    }
    
    
}