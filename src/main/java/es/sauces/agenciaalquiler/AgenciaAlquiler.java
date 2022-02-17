/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

import static es.sauces.agenciaalquiler.Grupo.A;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class AgenciaAlquiler {

    private String nombre;
    private List<Vehiculo> flota;

    public AgenciaAlquiler() {
    }

    public AgenciaAlquiler(String nombre, List<Vehiculo> flota) {
        this.nombre = nombre;
        this.flota = new ArrayList<>(flota);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }

    public void setFlota(List<Vehiculo> flota) {
        this.flota = flota;
    }
    
    

    public boolean incluirVehiculo(Vehiculo vehiculo) {
        boolean salida = false;
        if (vehiculo.getMatricula() != null) {
            salida = true;
            flota.add(vehiculo);
        }
        return salida;
    }

    public Vehiculo consultarVehiculo(String matricula) {
        for (Vehiculo v : flota) {
            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        boolean salida = false;

        if (vehiculo != null) {
            salida = flota.remove(vehiculo);
        }
        return salida;
    }

    public List<Vehiculo> listarVehiculosPorPrecio() {
        List<Vehiculo> salida = null;

        salida = new ArrayList<>(flota);
        Collections.sort(salida, new ComparadorPrecio());

        return salida;
    }

    public List<Vehiculo> listarVehiculos(Grupo grupo) {
        List<Vehiculo> salida = null;
        salida = new ArrayList<>(flota);
        Collections.sort(salida);
        return salida;
    }
    
    public Vehiculo getVehiculoMasBarato(){
       Vehiculo barato;
       barato= new Turismo(null,A,1);
       for (Vehiculo v : flota){
           v.getPrecioAlquiler();
           if(v.getPrecioAlquiler() >= barato.getPrecioAlquiler()){
           } else {
               barato=v;
           }
       }
       return barato;
    }

}
