/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Servidor {

    public static final int PUERTO = 4444;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            serverSocket = new ServerSocket(PUERTO);

            System.out.println("Escuchando: " + serverSocket);

            socket = serverSocket.accept();
            System.out.println("Connexión acceptada: " + socket);
            
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
            String mensaje;

            do {
                mensaje = bufferedReader.readLine();
                System.out.println("Cliente: " + mensaje);
                printWriter.println(mensaje);
            } while (!mensaje.equalsIgnoreCase("Adios"));

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                printWriter.close();
                bufferedReader.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
