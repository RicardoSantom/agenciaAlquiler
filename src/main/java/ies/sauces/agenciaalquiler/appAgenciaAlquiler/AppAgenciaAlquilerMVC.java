/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies.sauces.agenciaalquiler.appAgenciaAlquiler;

import ies.sauces.agenciaalquiler.controlador.Controlador;
import ies.sauces.agenciaalquiler.modelo.AgenciaAlquiler;
import ies.sauces.agenciaalquiler.vista.Ventana;

/**
 *
 * @author Usuario
 */
public class AppAgenciaAlquilerMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ventana vista=new Ventana();
        AgenciaAlquiler modelo=new AgenciaAlquiler();
        Controlador controlador=new Controlador(vista,modelo);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
    
}
