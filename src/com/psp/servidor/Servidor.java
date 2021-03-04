package com.psp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    private final int PORT = 9876;
    private ServerSocket serverSocket;
    private Socket socket;

    // Constructor
    Servidor() throws IOException {
        serverSocket = new ServerSocket(PORT); // Socket servidor
    }

    // Método para iniciar el servidor
    public void iniciarServidor() throws IOException {
        while(true){
            System.out.println("Servidor iniciado. Esperando cliente...");

            // Iniciamos el socket del servidor y esperamos una conexión desde un cliente
            socket = serverSocket.accept();
            System.out.println("¡Habemus conexión!");

            // Abrimos flujo de entrada en el servidor desde el cliente
            DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
            // Abrimos flujo de salida del servidor hacia el cliente
            DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());

            // Servidor confirma conexión al cliente y recibe un ok
            salidaServidor.writeUTF("¡Habemus conexión!");

            // Creamos el Array donde almacenar las tortugas
            ArrayList<Tortuga> tortugas = new ArrayList<>();

            // Recogemos las acciones que el usuario envía a través del menú del cliente
            int accion = entradaServidor.readByte();
            switch (accion) {
                case 1: // Introducir una nueva tortuga
                    salidaServidor.writeUTF("Has elegido la opción " + accion);
                    clienteDice(String.valueOf(accion));
                    salidaServidor.writeUTF("Introduce el nombre de la nueva tortuga:");
                    String nombre = entradaServidor.readUTF();
                    salidaServidor.writeUTF("¿Dorsal?");
                    int dorsal = entradaServidor.readByte();
                    Tortuga tortuga = new Tortuga(nombre, dorsal);
                    tortugas.add(tortuga);
                    salidaServidor.writeUTF("Tortuga registrada correctamente.");
                    break;

                case 2: // Eliminar una tortuga
                    clienteDice(String.valueOf(accion));
                    salidaServidor.writeUTF("Has elegido la opción " + accion);
                    break;
                case 3: // Mostrar tortugas
                    clienteDice(String.valueOf(accion));
                    salidaServidor.writeUTF("Has elegido la opción " + accion);
                    salidaServidor.writeUTF("Tortugas registradas:");
                    for (int i = 0; i < tortugas.size() ; i++) {
                        salidaServidor.writeUTF(tortugas.get(i).toString());
                    }
                    break;
                case 4: // Iniciar carrera
                    clienteDice(String.valueOf(accion));
                    salidaServidor.writeUTF("Has elegido la opción " + accion);
                    break;
                case 5: // Salir
                    // System.out.println("\nSalir.");
                    clienteDice(String.valueOf(accion));
                    salidaServidor.writeUTF("Has elegido la opción " + accion);
                    break;
            }
            // Mensaje en el servidor cuando el cliente se desconecta
            System.out.println("¡Cliente desconectado!");
        }

    }

    private void clienteDice (String mensaje){
        System.out.println("Cliente: " + mensaje);
    }

}
