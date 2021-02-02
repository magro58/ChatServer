
package Client;

import java.io.BufferedReader;

/**
 *@author Magrini
 */
public class Read extends Thread
{     
    public BufferedReader input;
        
        public Read(BufferedReader input)
        {
            this.input=input;
        }
        
        public void run()
        {
            String stringa;
            try
            {
                for(;;)
                {
                    stringa=input.readLine();
                    System.out.println(stringa);
                }
            }
            catch (Exception e)
            {
                System.out.println("Errore lettura dati. \n");
            }
        }
        
        public void ending()
        {
            this.stop();
        }
    }