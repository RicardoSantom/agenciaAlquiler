package es.sauces.agenciaalquiler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public abstract class Vehiculo implements Comparable<Vehiculo>,Serializable {

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
        this.matricula = matricula;
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
        this.matricula = matricula;
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
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.matricula);
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
