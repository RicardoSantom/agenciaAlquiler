/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

import java.util.Objects;

/**
 *
 * @author daw1
 */
public abstract class Vehiculo implements Comparable<Vehiculo> {
    private String matricula;
    private Grupo grupo;
    
    public Vehiculo(){}

    public Vehiculo(String matricula, Grupo grupo) {
        this.matricula = matricula;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    

    @Override
    public String toString() {
        return  matricula + "," + grupo ;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    public int compareTo(Vehiculo v){
        int num;
        
        num=this.matricula.compareTo(matricula);
        
        return num;
    }
    
    public abstract float getPrecioAlquiler();
    
    public float getPrecioAlquiler(int dias){
        float precio;
        
        precio=getPrecioAlquiler()*dias;
        
        return precio;
    }
}
