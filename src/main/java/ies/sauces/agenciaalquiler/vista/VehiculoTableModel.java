/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies.sauces.agenciaalquiler.vista;

import ies.sauces.agenciaalquiler.modelo.Furgoneta;
import ies.sauces.agenciaalquiler.modelo.Turismo;
import ies.sauces.agenciaalquiler.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class VehiculoTableModel extends AbstractTableModel{
    private List<Vehiculo> listadoVehiculos;
    private String[] columnas;

    public VehiculoTableModel() {
        this.listadoVehiculos = new ArrayList<>();
        this.columnas = new String[]{"MATRÍCULA","GRUPO","TIPO","PLAZAS","CAPACIDAD","PRECIO ALQUILER"};
    }

    @Override
    public int getRowCount() {
        return listadoVehiculos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vehiculo v = listadoVehiculos.get(rowIndex);
        Object o=null;
        switch(columnIndex){
            case 0:
                o=v.getMatricula().toString();
                break;
            case 1:
                o=v.getGrupo().toString().toUpperCase();
                break;
            case 2:
                o=v.getClass().getSimpleName().toUpperCase();
                break;
            case 3:
                if(v instanceof Turismo){
                    o=((Turismo) v).getPlazas();
                }else{
                    o=0;
                }
                break;
            case 4:
                if(v instanceof Furgoneta){
                    o=((Furgoneta) v).getCapacidad();
                }else{
                    o=0;
                }
                break;
            case 5:
                o=v.getPrecioAlquiler();
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase=null;
        switch(columnIndex){
            case 0:
                clase=String.class;
                break;
            case 1:
                clase=String.class;
                break;
            case 2:
                clase=String.class;
                break;
            case 3:
                clase=Integer.class;
                break;
            case 4:
                clase=Float.class;
                break;
            case 5:
                clase=Float.class;
                break;
        }
        return clase;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void setListadoVehiculos(List<Vehiculo> listado) {
        this.listadoVehiculos = listado;
        this.fireTableDataChanged();
    }
    
    
    
    
}
