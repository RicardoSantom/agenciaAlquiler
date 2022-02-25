/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class Turismo extends Vehiculo {

    private int plazas;

    /**
     *
     */
    public Turismo() {
    }

    /**
     *
     * @param matricula
     * @param grupo
     * @param plazas
     */
    public Turismo(Matricula matricula, Grupo grupo, int plazas) {
        super(matricula, grupo);
        this.plazas = plazas;
    }

    /**
     *
     * @return
     */
    public int getPlazas() {
        return plazas;
    }

    /**
     *
     * @param plazas
     */
    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public String toString() {
        return super.toString() + "," + plazas;
    }

    /**
     *
     * @return
     */
    @Override
    public float getPrecioAlquiler() {
        return getGrupo().getBase()+getGrupo().getVariableTurismo()*plazas;

    }

    /**
     *
     * @param dias
     * @return
     */
    @Override
    public float getPrecioAlquiler(int dias) {
        float precio;

        precio = getPrecioAlquiler() * dias;

        return precio;
    }

}
