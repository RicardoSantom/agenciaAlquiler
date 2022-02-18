/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class AgenciaAlquiler {

    private String nombre;
    private List<Vehiculo> flota;

    /**
     *
     */
    public AgenciaAlquiler() {
        flota = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public List<Vehiculo> getFlota() {
        return flota;
    }

    /**
     *
     * @param flota
     */
    public void setFlota(List<Vehiculo> flota) {
        this.flota = flota;
    }

    /**
     *
     * @param vehiculo
     * @return
     */
    public boolean incluirVehiculo(Vehiculo vehiculo) {
        boolean salida = false;

        if (vehiculo != null && !flota.contains(vehiculo)) {
            salida = flota.add(vehiculo);
        }

        return salida;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public Vehiculo consultarVehiculo(String matricula) {
        //Con listiterator
        /*Vehiculo salida=null;
        ListIterator<Vehiculo> li=flota.listIterator();
        
        while(li.hasNext()){
            salida=li.next();
            if(salida.getMatricula().equals(matricula)){
                return salida;
            }
        }
        return null;*/
        for (Vehiculo v : flota) {
            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    /**
     *
     * @param vehiculo
     * @return
     */
    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        boolean salida = false;

        if (vehiculo != null) {
            salida = flota.remove(vehiculo);
        }
        return salida;
    }

    /**
     *
     * @return
     */
    public List<Vehiculo> listarVehiculosPorPrecio() {
        List<Vehiculo> salida = null;

        salida = new ArrayList<>(flota);
        Collections.sort(salida, new ComparadorPrecio());

        return salida;
    }

    /**
     *
     * @param grupo
     * @return
     */
    public List<Vehiculo> listarVehiculos(Grupo grupo) {
        List<Vehiculo> salida = null;
        salida = new ArrayList<>();
        for(Vehiculo vehiculo : flota){
            if(vehiculo.getGrupo()==grupo){
                salida.add(vehiculo);
            }
        }
        return salida;
    }

    /**
     *
     * @return
     */
    public Vehiculo getVehiculoMasBarato() {
        Vehiculo barato;
        barato = Collections.min(flota, new ComparadorPrecio());
        return barato;
    }

}
