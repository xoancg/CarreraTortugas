package com.psp.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    // Datos de conexión
    private final String HOST = "localhost";    // Dirección
    private final int PORT = 9876;              // Puerto
    private final Socket socket;                // Socket

    public Cliente() throws IOException {
        socket = new Socket(HOST, PORT);
    }

    // Instanciamos un objeto tipo Scanner para que el usuario pueda introducir mensajes vía consola
    Scanner teclado = new Scanner(System.in);

    public void iniciarCliente() throws IOException {
        System.out.println("Iniciando conexión con el servidor...");

        // Abrimos flujo de entrada en el servidor desde el cliente
        DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());

        // Abrimos flujo de salida del servidor hacia el cliente
        DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());

        // El servidor confirma la conexión al cliente y respondemos ok
        servidorDice(entradaCliente.readUTF());

        // Mostar menú
        mostrarMenu(entradaCliente, salidaCliente);

        // Fin de programa
        System.out.println("Programa finalizado. ¡Hasta pronto!");

    }

    private void servidorDice (String mensaje){
        System.out.println("Servidor: " + mensaje);
    }

    public void mostrarMenu(DataInputStream entradaCliente, DataOutputStream salidaCliente) throws IOException {
        int accion;
        do {
            System.out.println("\nMENÚ:\n" + "1. Introducir una nueva tortuga\n"
                    + "2. Eliminar una tortuga\n" + "3. Mostrar tortugas\n" + "4. Iniciar carrera\n"
                    + "5. Salir\n");

            // Acción seleccionada por el usuario
            accion = teclado.nextInt(); // Recuperas entero
            teclado.nextLine(); // Eliminamos salto de línea
            System.out.println("Acción: " + accion);
            // Consume salto de línea
            // https://es.stackoverflow.com/questions/121684/problema-clase-scanner-en-java-al-introducir-varios-strings-como-variables
        } while (accion < 1 || accion > 5);
        switch (accion) {
            case 1: // Introducir una nueva tortuga
                salidaCliente.writeByte(accion);                    // Enviamos opción seleccionada
                servidorDice(entradaCliente.readUTF());             // Servidor confirma opción elegida
                introducirTortuga(entradaCliente, salidaCliente);
                break;
            case 2: // Eliminar una tortuga
                salidaCliente.writeByte(accion);                    // Enviamos opción seleccionada
                servidorDice(entradaCliente.readUTF());             // Servidor confirma opción elegida
                eliminarTortuga(entradaCliente, salidaCliente);
                break;
            case 3: // Mostrar tortugas
                salidaCliente.writeByte(accion);                    // Enviamos opción seleccionada
                servidorDice(entradaCliente.readUTF());             // Servidor confirma creación de tortuga
                servidorDice(entradaCliente.readUTF());             // otra cosa? Ver!
                mostrarTortugas(entradaCliente, salidaCliente);
                break;
            case 4: // Iniciar carrera
                salidaCliente.writeByte(accion);                    // Enviamos opción seleccionada
                servidorDice(entradaCliente.readUTF());             // Servidor confirma opción elegida
                iniciarCarrera(entradaCliente, salidaCliente);
                break;
            case 5: // Salir
                // System.out.println("\nSalir.");
                salidaCliente.writeByte(accion);                    // Enviamos opción seleccionada
                servidorDice(entradaCliente.readUTF());             // Servidor confirma opción elegida
                servidorDice(entradaCliente.readUTF());             // Servidor confirma cierre de conexiones y del programa
                socket.close();
                break;
        }
    }

    public void introducirTortuga(DataInputStream entradaCliente, DataOutputStream salidaCliente) throws IOException {
        servidorDice(entradaCliente.readUTF());         // Servidor pregunta nombre de tortuga
        salidaCliente.writeUTF(teclado.nextLine());     // Indicamos el nombre de la tortuga
        servidorDice(entradaCliente.readUTF());         // Servidor pregunta dorsal
        salidaCliente.writeByte(teclado.nextInt());    // Enviamos dorsal
        teclado.nextLine();                             // Limpiar buffer
        servidorDice(entradaCliente.readUTF());         // Servidor confirma creación de tortuga
        mostrarMenu(entradaCliente, salidaCliente);
    }

    public void mostrarTortugas(DataInputStream entradaCliente, DataOutputStream salidaCliente) throws IOException {
        int numeroTortugas = entradaCliente.readByte();
        for (int i = 0; i < numeroTortugas; i++) {
            servidorDice(entradaCliente.readUTF());
        }
        mostrarMenu(entradaCliente, salidaCliente);
    }

    public void iniciarCarrera(DataInputStream entradaCliente, DataOutputStream salidaCliente) throws IOException {

        servidorDice(entradaCliente.readUTF());         // Servidor comunica el nombre de la tortuga ganadora
        mostrarMenu(entradaCliente, salidaCliente);
    }

    public void eliminarTortuga(DataInputStream entradaCliente, DataOutputStream salidaCliente) throws IOException{
        servidorDice(entradaCliente.readUTF());         // Servidor pregunta la posición de la tortuga a eliminar
        salidaCliente.writeByte(teclado.nextInt());     // Cliente indica la posición de la tortuga
        teclado.nextLine();                             // Limpiar buffer después de introducir un entero
        servidorDice(entradaCliente.readUTF());         // Servidor confirma operación realizada
        mostrarMenu(entradaCliente, salidaCliente);
    }
}
