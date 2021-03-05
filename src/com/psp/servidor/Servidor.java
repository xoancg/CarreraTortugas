package com.psp.servidor;

import com.psp.cliente.Main;

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

    // Creamos el Array donde almacenar las tortugas
    ArrayList<Tortuga> tortugas = new ArrayList<>();

    // Método para iniciar el servidor
    public void iniciarServidor() throws IOException, InterruptedException {
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

            int accion = entradaServidor.readByte();

            try {

                do {
                    // Recogemos las acciones que el usuario envía a través del menú del cliente
                    switch (accion) {
                        case 1: // Introducir una nueva tortuga
                            salidaServidor.writeUTF("Has elegido la opción " + accion);
                            clienteDice(String.valueOf(accion));
                            introducirTortuga(entradaServidor, salidaServidor);
                            break;

                        case 2: // Eliminar una tortuga
                            clienteDice(String.valueOf(accion));
                            salidaServidor.writeUTF("Has elegido la opción " + accion);
                            eliminarTortuga(entradaServidor, salidaServidor);
                            break;
                        case 3: // Mostrar tortugas
                            clienteDice(String.valueOf(accion));
                            salidaServidor.writeUTF("Has elegido la opción " + accion);
                            mostrarTortugas(entradaServidor, salidaServidor);
                            break;
                        case 4: // Iniciar carrera
                            clienteDice(String.valueOf(accion));
                            salidaServidor.writeUTF("Has elegido la opción " + accion);
                            // Metemos a las tortugas en la carrera
                            Carrera carrera = new Carrera(tortugas);
                            // Arrancamos
                            carrera.iniciarCarrera();
                            // Esperamos fin
                            carrera.terminarCarrera();
                            salidaServidor.writeUTF("Ha ganado la tortuga: " + carrera.getGanadora());
                            break;
                        case 5: // Salir
                            clienteDice(String.valueOf(accion));
                            salidaServidor.writeUTF("Has elegido la opción " + accion);
                            salidaServidor.writeUTF("Cerrando conexión...");
                            break;
                    } // switch

                    accion = entradaServidor.readByte();
                } while (accion != 5);

            // Capturamos el error producido cuando el usuario marca la opción 5 y cerramos conexiones
            } catch (Exception e){
                serverSocket.close();
                socket.close();
                System.out.println("Conexiones cerradas a solicitud del cliente.");
            }

            // Mensaje en el servidor cuando el cliente se desconecta
            System.out.println("No hay conexiones activas.");
            // Cerramos el programa
            System.exit(0);
        }

    }

    private void clienteDice (String mensaje){
        System.out.println("Cliente: " + mensaje);
    }

    public void introducirTortuga(DataInputStream entradaServidor, DataOutputStream salidaServidor) throws IOException {
        salidaServidor.writeUTF("Introduce el nombre de la nueva tortuga:");
        String nombre = entradaServidor.readUTF();
        salidaServidor.writeUTF("¿Dorsal?");
        int dorsal = entradaServidor.readByte();
        Tortuga tortuga = new Tortuga(nombre, dorsal);
        tortugas.add(tortuga);
        salidaServidor.writeUTF("Tortuga registrada correctamente.");
    }

    public void mostrarTortugas(DataInputStream entradaServidor, DataOutputStream salidaServidor) throws IOException {
        salidaServidor.writeUTF("Tortugas registradas:");
        salidaServidor.writeByte(tortugas.size());
        int numero = 1;
        for (int i = 0; i < tortugas.size() ; i++) {
            salidaServidor.writeUTF(numero + ". " + tortugas.get(i).toString());
            numero++;
        }
    }

    public void eliminarTortuga (DataInputStream entradaServidor, DataOutputStream salidaServidor) throws IOException {
        salidaServidor.writeUTF("Introduce la posición (desde 1) de la tortuga que quieres eliminar (o pulsa 0 para borrarlas todas):");
        int posicion = entradaServidor.readByte();
        System.out.println("Borrar posición:" + posicion);
        if(posicion == 0) {
            tortugas.clear();
            salidaServidor.writeUTF("Se han borrado todas las tortugas.");
        } else {
            posicion = posicion - 1;
            tortugas.remove(posicion);
            salidaServidor.writeUTF("Se ha borrado la tortuga indicada en la posición " + posicion + 1);
        }
    }
}//s
