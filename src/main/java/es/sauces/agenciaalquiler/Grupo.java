package es.sauces.agenciaalquiler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ricardo Santiago Tomé
 */
public enum Grupo {

    /**
     *
     */
    A(50,1.5f,10),

    /**
     *
     */
    B(55,2,15),

    /**
     *
     */
    C(60,2.5f,20);

    private final int base,variableFurgoneta;
    private final float variableTurismo;

    private Grupo(int base, float variableTurismo, int variableFurgoneta) {
        this.base = base;
        this.variableFurgoneta = variableFurgoneta;
        this.variableTurismo = variableTurismo;
    }

    public int getBase() {
        return base;
    }

    public int getVariableFurgoneta() {
        return variableFurgoneta;
    }

    public float getVariableTurismo() {
        return variableTurismo;
    }
    
    
    
}
