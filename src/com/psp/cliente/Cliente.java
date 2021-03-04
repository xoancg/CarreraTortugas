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
    private final Socket socket;              // Socket

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
        int accion;
        do {
            System.out.println("\nMENÚ:\n" + "1. Introducir una nueva tortuga\n"
                    + "2. Eliminar una tortuga\n" + "3. Mostrar tortugas\n" + "4. Iniciar carrera\n"
                    + "5. Salir\n");

            // Acción seleccionada por el usuario
            accion = Integer.parseInt(teclado.nextLine()); // Consume salto de línea
            // https://es.stackoverflow.com/questions/121684/problema-clase-scanner-en-java-al-introducir-varios-strings-como-variables
        } while (accion < 1 || accion > 5);
        switch (accion) {
            case 1: // Introducir una nueva tortuga
                salidaCliente.writeByte(1);
                servidorDice(entradaCliente.readUTF());
                break;
            case 2: // Eliminar una tortuga
                salidaCliente.writeByte(2);
                servidorDice(entradaCliente.readUTF());
                break;
            case 3: // Mostrar tortugas
                salidaCliente.writeByte(3);
                servidorDice(entradaCliente.readUTF());
                break;
            case 4: // Iniciar carrera
                salidaCliente.writeByte(4);
                servidorDice(entradaCliente.readUTF());
                break;
            case 5: // Salir
                // System.out.println("\nSalir.");
                salidaCliente.writeByte(5);
                servidorDice(entradaCliente.readUTF());
                break;
        }

    }

    private void servidorDice (String mensaje){
        System.out.println("Servidor: " + mensaje);
    }
}
