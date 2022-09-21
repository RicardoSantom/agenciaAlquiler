package ies.sauces.agenciaalquiler.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/main/java/ies/sauces/agenciaalquiler/modelo/Furgoneta.java

=======
package es.sauces.agenciaalquiler.modelo;
>>>>>>> Stashed changes:src/main/java/ies/sauces/agenciaalquiler/modelo/modelo/Furgoneta.java

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class Furgoneta extends Vehiculo{
    private float capacidad;

    /**
     *
     */
    public Furgoneta() {
    }

    /**
     *
     * @param matricula
     * @param grupo
     * @param capacidad
     */
    public Furgoneta( Matricula matricula, Grupo grupo,float capacidad ) {
        super(matricula, grupo);
        this.capacidad = capacidad;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "," + capacidad;
    }
    
    /**
     *
     * @return
     */
    @Override
    public float getPrecioAlquiler() {
        return getGrupo().getBase()+getGrupo().getVariableFurgoneta()*capacidad;

    }

    /**
     *
     * @param dias
     * @return
     */
    @Override
    public float getPrecioAlquiler(int dias) {
        return super.getPrecioAlquiler(dias);
    }
    
    

}
