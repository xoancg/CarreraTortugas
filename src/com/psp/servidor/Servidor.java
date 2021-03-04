package com.psp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private final int PORT = 9876;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    // Constructor
    Servidor() throws IOException {
        serverSocket = new ServerSocket(PORT); // Socket servidor
    }

    // Método para iniciar el servidor
    public void iniciarServidor() throws IOException {
        while(true){
            System.out.println("Servidor iniciado. Esperando cliente...");

            // Iniciamos el socket del servidor y esperamos una conexión desde un cliente
            clientSocket = serverSocket.accept();
            System.out.println("¡Habemus conexión! Esperando nombre del cliente...");

            // Abrimos flujo de entrada en el servidor desde el cliente
            DataInputStream entradaServidor = new DataInputStream(clientSocket.getInputStream());
            // Abrimos flujo de salida del servidor hacia el cliente
            DataOutputStream salidaServidor = new DataOutputStream(clientSocket.getOutputStream());
        }

    }
}
