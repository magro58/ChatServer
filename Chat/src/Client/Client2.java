/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.*;
import java.net.*;

/**
 *
 * @author informatica
 */
public class Client2 {

    String nome_server="localhost";
    int porta_server=2006;
    Socket socket;
    String risposta;
    String messaggio;
    BufferedReader input_tastiera;
    DataOutputStream dati_al_server;
    BufferedReader dati_dal_server;

}
