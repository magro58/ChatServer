
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *@author Magrini
 */
public class Write extends Thread
{
   public Socket sock;
   public BufferedReader Keyin;
   public BufferedReader input;
   public DataOutputStream output;
   public Read read;

    public Write(BufferedReader Keyin, BufferedReader input, DataOutputStream output, Socket sock, Read read) 
    {
        this.Keyin = Keyin;
        this.input = input;
        this.output = output;
        this.sock = sock;
        this.read = read;
    }

        public void run()
        {
            String msg;
            try
            {
                for(;;)
                {
                    msg=Keyin.readLine();
                    if(msg.equals("stop"))
                    {
                        output.writeBytes(msg+ "\n");
                        read.ending();
                        Keyin.close();
                        input.close();
                        output.close();
                        sock.close();
                        this.stop();
                    }
                    else
                    {
                        output.writeBytes(msg+ "\n");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        } 
}