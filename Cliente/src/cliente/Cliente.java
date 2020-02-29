/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Cliente {

    public static final int PUERTO = 4444;
    public static final String DIRECCION = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedReader entradaTeclado = null;
        PrintWriter printWriter = null;

        try {
            InetSocketAddress address = new InetSocketAddress(DIRECCION, PUERTO);
            socket = new Socket();
            socket.connect(address);
            
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
            
            String mensaje;
            
            do {                
                System.out.println("Entrada: ");
                mensaje = entradaTeclado.readLine();
                
                printWriter.println(mensaje);
                printWriter.flush();
                
                mensaje = bufferedReader.readLine();
                System.out.println("Respuesta Servidor: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("adios"));
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
