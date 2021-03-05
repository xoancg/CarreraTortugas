package com.psp.servidor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Instanciamos el servidor
        Servidor serverSocket = new Servidor();

        // Iniciamos el servidor
        serverSocket.iniciarServidor();
    }
}
