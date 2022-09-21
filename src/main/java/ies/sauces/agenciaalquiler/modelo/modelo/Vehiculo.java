package ies.sauces.agenciaalquiler.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/main/java/ies/sauces/agenciaalquiler/modelo/Vehiculo.java
=======
package es.sauces.agenciaalquiler.modelo;

>>>>>>> Stashed changes:src/main/java/ies/sauces/agenciaalquiler/modelo/modelo/Vehiculo.java
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public abstract class Vehiculo implements Comparable<Vehiculo>, Serializable {

    private Matricula matricula;
    private Grupo grupo;

    /**
     *
     */
    public Vehiculo() {
    }

    /**
     *
     * @param matricula
     * @param grupo
     */
    public Vehiculo(Matricula matricula, Grupo grupo) {
        if(matricula!=null){
            this.matricula = matricula;
        }
        this.grupo = grupo;
    }

    /**
     *
     * @return
     */
    public Matricula getMatricula() {
        return matricula;
    }

    /**
     *
     * @param matricula
     */
    public void setMatricula(Matricula matricula) {
        if(matricula!=null){
         this.matricula = matricula;   
        }
    }

    /**
     *
     * @return
     */
    public Grupo getGrupo() {
        return grupo;
    }

    /**
     *
     * @param grupo
     */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return matricula + "," + grupo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof Vehiculo) {

            final Vehiculo other = (Vehiculo) obj;
            if (Objects.equals(this.matricula, other.matricula)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Vehiculo v) {
        int num;

        num = this.matricula.compareTo(v.matricula);

        return num;
    }

    /**
     *
     * @return
     */
    public abstract float getPrecioAlquiler();

    /**
     *
     * @param dias
     * @return
     */
    public float getPrecioAlquiler(int dias) {
        float precio;

        precio = getPrecioAlquiler() * dias;

        return precio;
    }
}
