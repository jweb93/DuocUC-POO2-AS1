package model;

import interfaces.*;

/**
 * Representa un pedido de Comida que puede requerirse caliente o no
 */

public class PedidoComida extends Pedido implements Despachable, Cancelable, Rastreable, Entregable, Reservable {

    private boolean caliente;

    // Constructor
    public PedidoComida(int idPedido, Direccion direccionEntrega, double distanciaKM, boolean caliente) {
        super(idPedido, direccionEntrega, distanciaKM);
        this.caliente = caliente;
    }

    // Getter and Setters
    public boolean isCaliente() {
        return caliente;
    }

    public void setCaliente(boolean caliente) {
        this.caliente = caliente;
    }


    // Implementación de métodos abstractos
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(15 + (2 * getDistanciaKM()));
    }

    // Sobreescritura
    @Override
    public void mostrarResumen() {
        System.out.println("Pedido Comida N°: " + getIdPedido());
        System.out.println("Destino: " + getDireccionEntrega());
        System.out.println("Distancia (km): " + getDistanciaKM());
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    @Override
    public void asignarRepartidor() {
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando un/a repartidor/a para entregar su pedido de comida N° " + getIdPedido());
            agregaHistorial("Se asignó un/a repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    @Override
    public void asignarRepartidor(String nombre) {
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando a " + nombre + " para entregar su pedido de comida N° " + getIdPedido());
            agregaHistorial("Se asignó a " + nombre + " como repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    // Implementación de interfaces

    @Override
    public void reservar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha reservado el pedido de comida N°: " + getIdPedido());
            setEstado("En preparación");
        }else{
            System.out.println("No fue posible reservar el pedido de comida N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void cancelar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha cancelado el pedido de comida N°: " + getIdPedido());
            setEstado("Cancelado");
        }else{
            System.out.println("No fue posible cancelar el pedido de comida N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void despachar() {
        if(getEstado().equals("En preparación")){
            System.out.println("Se ha despachado el pedido de comida N°: " + getIdPedido());
            setEstado("En despacho");
        }else{
            System.out.println("No fue posible despachar el pedido de comida N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void entregar() {
        if(getEstado().equals("En despacho")){
            System.out.println("Se ha entregado el pedido de comida N°: " + getIdPedido());
            setEstado("Entregado");
        }else{
            System.out.println("No fue posible entregar el pedido de comida N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }
    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido de comida N°: " + getIdPedido());
        int i = 1;
        for(String evento: getHistorial()){
            System.out.println(i + ". " + evento);
            i += 1;
        }
    }


}
