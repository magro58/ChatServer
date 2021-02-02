
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 *@author Magrini
 */
public class Client 
{
   public Socket sock;
   public BufferedReader Keyin;
   public BufferedReader input;
   public DataOutputStream output;
   public Write write;
   public Read read;
    
    public static void main(String[] args) 
    {
            Client c= new Client();
            c.connect();
    }

    public void connect()
    {
        try
        {
            InetAddress ip= InetAddress.getByName("localhost");
            sock= new Socket(ip, 4567);
            Keyin = new BufferedReader(new InputStreamReader(System.in));
            output= new DataOutputStream(sock.getOutputStream());
            input= new BufferedReader(new InputStreamReader(sock.getInputStream()));            
            read= new Read(input);
            write = new Write(Keyin, input, output, sock, read);
            write.start();
            read.start();
        }
        catch(Exception e)
        {
            System.out.println("Errore di connessione... \n");
        }
    }   
}

