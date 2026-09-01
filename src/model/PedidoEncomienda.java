package model;

import interfaces.*;

/**
 * Representa un pedido de Encomienda que puede ser fragil o no
 */

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable, Rastreable, Entregable, Reservable {
    private boolean fragil;

    // Constructor
    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, double distanciaKM, boolean fragil) {
        super(idPedido, direccionEntrega, distanciaKM);
        this.fragil = fragil;
    }

    // Getter and Setters
    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    // Implementación del método abstracto
    @Override
    public int calcularTiempoEntrega(){
        return (int) Math.round(20 + (1.5 * getDistanciaKM()));
    }

    // Sobreescritura
    @Override
    public void mostrarResumen(){
        System.out.println("Pedido Encomienda N°: " + getIdPedido());
        System.out.println("Destino: " + getDireccionEntrega());
        System.out.println("Distancia (km): " + getDistanciaKM());
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    @Override
    public void asignarRepartidor(){
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando un/a repartidor/a para entregar su encomienda N° " + getIdPedido());
            agregaHistorial("Se asignó un/a repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    @Override
    public void asignarRepartidor(String nombre){
        if(getEstado().equals("En preparación")){
            System.out.println("Asignando a " + nombre + " para entregar su encomienda N° " + getIdPedido());
            agregaHistorial("Se asignó a " + nombre + " como repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }
    }

    // Implementación de interfaces

    @Override
    public void reservar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha reservado la encomienda N°: " + getIdPedido());
            setEstado("En preparación");
        }else{
            System.out.println("No fue posible reservar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void cancelar() {
        if(getEstado().equals("Por reservar")){
            System.out.println("Se ha cancelado la encomienda N°: " + getIdPedido());
            setEstado("Cancelado");
        }else{
            System.out.println("No fue posible cancelar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void despachar() {
        if(getEstado().equals("En preparación")){
            System.out.println("Se ha despachado la encomienda N°: " + getIdPedido());
            setEstado("En despacho");
        }else{
            System.out.println("No fue posible despachar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void entregar() {
        if(getEstado().equals("En despacho")){
            System.out.println("Se ha entregado la encomienda N°: " + getIdPedido());
            setEstado("Entregado");
        }else{
            System.out.println("No fue posible entregar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }
    @Override
    public void verHistorial() {
        System.out.println("Historial de la encomienda N°: " + getIdPedido());
        int i = 1;
        for(String evento: getHistorial()){
            System.out.println(i + ". " + evento);
            i += 1;
        }
    }
}
