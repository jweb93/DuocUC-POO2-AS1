package model;

import java.util.ArrayList;

/**
 * Representa un pedido que debe ser entregado a una dirección
 */

public abstract class  Pedido {
    private int idPedido;
    private Direccion direccionEntrega;
    private double distanciaKM;
    private ArrayList<String> historial;
    private String estado; // En preparación, En despacho, Entregado, Cancelado

    public Pedido(int idPedido, Direccion direccionEntrega, double distanciaKM){
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKM = distanciaKM;
        this.historial = new ArrayList<>();
        this.estado = "Por reservar";
        agregaHistorial(this.estado);
    }

// Getter and Setters


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(double distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        agregaHistorial(estado);
    }

    // Método base implementado para mostrar resumen
    public void mostrarResumen(){
        System.out.println("Pedido N°: " + idPedido);
        System.out.println("Destino: " + direccionEntrega);
        System.out.println("Distancia (km): " + distanciaKM);
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    // Método base implementado para asignar repartidor genérico
    public void asignarRepartidor(){
        System.out.println("Asignando un repartidor para su pedido");
    }

    // Método base implementado para asignar repartidor específico
    public void asignarRepartidor(String nombre){
        System.out.println("Asignando a " + nombre + " para entregar su pedido");
    }

    // Método abstracto que deberá ser implementado por las subclases.
    public abstract int calcularTiempoEntrega();

    // Método adicional para agregar evento al historial
    public void agregaHistorial(String evento){
        this.historial.add(evento);
    }

}

