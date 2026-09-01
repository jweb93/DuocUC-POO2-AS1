package ui;

import interfaces.*;
import model.*;

import java.util.ArrayList;

/**
 * Clase principal para ejecutar el programa
 */

public class Main {

    public static void main(String[] args) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        // Creación de pedidos y calculo de tiempo estimado mediante llamada interna del constructor
        pedidos.add(new PedidoComida(
                1,
                new Direccion("Avenida Matta", 1042, "Santiago"),
                4,
                true
        ));

        pedidos.add(new PedidoEncomienda(
                2,
                new Direccion("Avenida Central", 987, "Maipú"),
                6,
                true
        ));

        pedidos.add(new PedidoExpress(
                3,
                new Direccion("Avenida Presidente Riesco", 777, "Las Condes"),
                7,
                "Farmacia"
        ));

        // Revisión de pedidos creados visualizando el tiempo estimado
        for(Pedido p : pedidos){
            System.out.println("------------- CREACIÓN DE PEDIDO");
            p.mostrarResumen();
            System.out.println();
        }

        // Cancelamos el primer pedido
        System.out.println("------------- CANCELACIÓN DE PEDIDO");
        ((Cancelable) pedidos.get(0)).cancelar();
        System.out.println();

        // Reservamos los pedidos. El primero debería fallar
        for(Pedido pedido : pedidos){
            System.out.println("------------- RESERVA DE PEDIDO");
            ((Reservable) pedido).reservar();
            System.out.println();
        }

        // Asignamos repartidores/as. El primero debería fallar
        System.out.println("------------- ASIGNACIÓN DE REPARTIDOR/A");
        pedidos.get(0).asignarRepartidor();
        System.out.println();

        System.out.println("------------- ASIGNACIÓN DE REPARTIDOR/A");
        pedidos.get(1).asignarRepartidor("Javier Rojas");
        System.out.println();

        System.out.println("------------- ASIGNACIÓN DE REPARTIDOR/A");
        pedidos.get(2). asignarRepartidor("Marie Curie");
        System.out.println();



        // Despachamos todos. El primero debería fallar.
        for(Pedido pedido : pedidos){
            System.out.println("------------- DESPACHO DE PEDIDO");
            ((Despachable) pedido).despachar();
            System.out.println();
        }

        // Entregamos el último pedido.
        System.out.println("------------- ENTREGA DE PEDIDO");
        ((Entregable) pedidos.get(2)).entregar();
        System.out.println();

        // Finalmente vemos el historial de los 3 pedidos

        for(Pedido pedido: pedidos){
            System.out.println("------------- HISTORIAL DE PEDIDO");
            ((Rastreable) pedido).verHistorial();
            System.out.println();
        }



    }
}
