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
public class Turismo extends Vehiculo{
    private int plazas;
    
    public Turismo(){}

    public Turismo(String matricula, Grupo grupo,int plazas) {
        super(matricula,grupo);
        this.plazas = plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public String toString() {
        return super.toString()+ "," + plazas ;
    }

    @Override
    public float getPrecioAlquiler() {
        float precio = 0;
        Grupo g;
        g=getGrupo();
        switch(g){
                case A:
                    precio=50 +(1.5f*getPlazas());
                    break;
                case B:
                    precio=55+(2*getPlazas());
                    break;
                case C:
                    precio=60+(2.5f*getPlazas());
                    break;
        }
        return precio;
        
    }
    
    @Override
    public float getPrecioAlquiler(int dias){
        float precio;
        
        precio=getPrecioAlquiler()*dias;
        
        return precio;
    }
    
}
