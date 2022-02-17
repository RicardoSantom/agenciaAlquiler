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
public class Furgoneta extends Vehiculo{
    private float capacidad;

    public Furgoneta() {
    }

    public Furgoneta( String matricula, Grupo grupo,float capacidad ) {
        super(matricula, grupo);
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return super.toString() + "," + capacidad;
    }
    
    
    @Override
    public float getPrecioAlquiler() {
        float precio=0;
        
        Grupo g;
        g=getGrupo();
        switch(g){
            case A:
                precio=50 +(5*capacidad);
                break;
            case B:
                precio=55+(10*capacidad);
                break;
            case C:
                precio=60+(15*capacidad);
        }
        
        return precio;
    }

    @Override
    public float getPrecioAlquiler(int dias) {
        return super.getPrecioAlquiler(dias);
    }
    
    

}
