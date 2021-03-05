package com.psp.cliente;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        try
        {
            // Instanciamos el cliente
            Cliente client = new Cliente();

            // Iniciamos la conexión desde el cliente
            client.iniciarCliente();
        } catch (Exception e){
            System.out.println("No se ha podido conectar con el servidor.\n" +
                    "Por favor, compruebe que el servidor está activo y reinicie el cliente.");
            System.err.println(e);
        }
    }

}
