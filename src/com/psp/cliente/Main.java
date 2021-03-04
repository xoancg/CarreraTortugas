package com.psp.cliente;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Instanciamos el cliente
        Cliente client = new Cliente();

        // Iniciamos la conexión desde el cliente
        client.iniciarCliente();
    }
}
