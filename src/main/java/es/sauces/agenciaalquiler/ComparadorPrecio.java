/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

import java.util.Comparator;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class ComparadorPrecio  implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo o1, Vehiculo o2) {
        int salida;
        salida = 0;
        float precio1, precio2;
        precio1 = o1.getPrecioAlquiler();
        precio2 = o2.getPrecioAlquiler();

        if (precio1 > precio2) {
            salida = 1;
        } else if (precio1 < precio2) {
            salida = -1;
        }

        return salida;
    
    }
    
}
