package model;

import interfaces.*;

/**
 * Representa un pedido de Express de algún comercio
 */

public class PedidoExpress extends Pedido implements Despachable, Cancelable, Rastreable, Entregable, Reservable {
    private String comercio;

    // Constructor
    public PedidoExpress(int idPedido, Direccion direccionEntrega, double distanciaKM, String comercio) {
        super(idPedido, direccionEntrega, distanciaKM);
        this.comercio = comercio; //Supermercado, Farmacia
    }

    // Getter and Setters
    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    // Implementación del método abstracto
    @Override
    public int calcularTiempoEntrega(){
        if (getDistanciaKM() > 5){
            return 15;
        }
        else{
            return 10;
        }
    }

    // Sobreescritura
    @Override
    public void mostrarResumen(){
        System.out.println("Pedido Express N°: " + getIdPedido());
        System.out.println("Destino: " + getDireccionEntrega());
        System.out.println("Distancia (km): " + getDistanciaKM());
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    @Override
    public void asignarRepartidor(){
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando un/a repartidor/a para entregar su pedido express N° " + getIdPedido());
            agregaHistorial("Se asignó un/a repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    @Override
    public void asignarRepartidor(String nombre){
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando a " + nombre + " para entregar su pedido express N° " + getIdPedido());
            agregaHistorial("Se asignó a " + nombre + " como repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }
    }

    // Implementación de interfaces
    @Override
    public void reservar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha reservado el pedido express N°: " + getIdPedido());
            setEstado("En preparación");
        }else{
            System.out.println("No fue posible reservar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void cancelar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha cancelado el pedido express N°: " + getIdPedido());
            setEstado("Cancelado");
        }else{
            System.out.println("No fue posible cancelar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void despachar() {
        if(getEstado().equals("En preparación")){
            System.out.println("Se ha despachado el pedido express N°: " + getIdPedido());
            setEstado("En despacho");
        }else{
            System.out.println("No fue posible despachar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void entregar() {
        if(getEstado().equals("En despacho")){
            System.out.println("Se ha entregado el pedido express N°: " + getIdPedido());
            setEstado("Entregado");
        }else{
            System.out.println("No fue posible entregar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }
    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido express N°: " + getIdPedido());
        int i = 1;
        for(String evento: getHistorial()){
            System.out.println(i + ". " + evento);
            i += 1;
        }
    }
}
