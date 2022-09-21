/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies.sauces.agenciaalquiler.controlador;

import ies.sauces.agenciaalquiler.modelo.AgenciaAlquiler;
import ies.sauces.agenciaalquiler.modelo.ComparadorPrecio;
import ies.sauces.agenciaalquiler.modelo.DaoException;
import ies.sauces.agenciaalquiler.modelo.Furgoneta;
import ies.sauces.agenciaalquiler.modelo.Grupo;
import ies.sauces.agenciaalquiler.modelo.Matricula;
import ies.sauces.agenciaalquiler.modelo.MatriculaException;
import ies.sauces.agenciaalquiler.modelo.Turismo;
import ies.sauces.agenciaalquiler.modelo.Vehiculo;
import ies.sauces.agenciaalquiler.modelo.VehiculoDao;
import ies.sauces.agenciaalquiler.modelo.VehiculoDaoCsv;
import ies.sauces.agenciaalquiler.modelo.VehiculoDaoJson;
import ies.sauces.agenciaalquiler.modelo.VehiculoDaoObj;
import ies.sauces.agenciaalquiler.modelo.VehiculoDaoXml;
import ies.sauces.agenciaalquiler.vista.Ventana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Controlador {

    private Ventana vista;
    private AgenciaAlquiler agenciaAlquiler;

    public Controlador(Ventana vista, AgenciaAlquiler agenciaAlquiler) {
        this.vista = vista;
        this.agenciaAlquiler = agenciaAlquiler;
    }

    public void iniciar() {
        vista.mostrar();
    }

    public void borrarVehiculo() {
        try {
            Matricula matricula = Matricula.valueOf(vista.getMatricula());
            Vehiculo v = agenciaAlquiler.consultarVehiculo(matricula);
            if (v == null) {
                vista.mostrarMensaje("El vehiculo con matricula " + matricula.toString() + " no existe");
            } else {
                if (agenciaAlquiler.eliminarVehiculo(v)) {
                    vista.mostrarMensaje("El vehiculo con matricula " + matricula.toString() + " ha sido eliminado");
                } else {

                    vista.mostrarMensaje("El vehiculo con matricula " + matricula.toString() + " no ha podido ser eliminado");
                }

            }
        } catch (MatriculaException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
        vista.limpiarCampos();
    }

    public void modificarVehiculo() {
        try {
            Matricula matricula = Matricula.valueOf(vista.getMatricula());
            Vehiculo v = agenciaAlquiler.consultarVehiculo(matricula);
            if (v != null) {

                if (v instanceof Turismo) {
                    ((Turismo) v).setPlazas(vista.getPlazas());
                } else {
                    ((Furgoneta) v).setCapacidad(vista.getCapacidad());
                }
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            } else {
                vista.mostrarMensaje("No hay una matricula seleccionada");
            }
        } catch (MatriculaException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    /**
     *
     */
    public void listarVehiculos() {
        String grupo = vista.getFiltroGrupo();
        List<Vehiculo> listado = null;
        List<Vehiculo> listado2 = null;
        if (!grupo.equals("TODOS")) {
            listado = agenciaAlquiler.listarVehiculos(Grupo.valueOf(grupo));
        } else {
            listado = new ArrayList<>(agenciaAlquiler.getFlota());
        }
        listado2 = new ArrayList<>();
        String tipo = vista.getFiltroTipo();
        switch (tipo) {
            case "TODOS":
                listado2 = new ArrayList<>(listado);
                break;
            case "TURISMO":
                listado2 = new ArrayList<>();
                for (Vehiculo v : listado) {
                    if (v instanceof Turismo) {
                        listado2.add(v);
                    }
                }
                break;
            case "FURGONETA":
                listado2 = new ArrayList<>();
                for (Vehiculo v : listado) {
                    if (v instanceof Furgoneta) {
                        listado2.add(v);
                    }
                }
                break;
        }
        switch (vista.getOrden()) {
            case "ALQUILER":
                listado2.sort(new ComparadorPrecio());

                break;
        }
        vista.listarVehiculos(listado2);
    }

    public void crearVehiculo() {
        Vehiculo v = new Turismo();
        try {
            Matricula matricula = Matricula.valueOf(vista.getMatricula());
            Grupo grupo = Grupo.valueOf(vista.getGrupo());
            String tipo = vista.getTipo();
            switch (tipo) {
                case "TURISMO":
                    int plazas = vista.getPlazas();
                    v = new Turismo(matricula, grupo, plazas);
                    break;
                case "FURGONETA":
                    float capacidad = vista.getCapacidad();
                    v = new Furgoneta(matricula, grupo, capacidad);
                    break;
                
            }

            /*agenciaAlquiler.incluirVehiculo(v);*/
            if (agenciaAlquiler.incluirVehiculo(v)) {
                listarVehiculos();
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
                vista.mostrarMensaje("Vehiculo incluido con exito");
            } else {
                vista.mostrarMensaje("No se ha podido incluir el vehiculo");
            }
        } catch (MatriculaException | NullPointerException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void guardarVehiculos() {
        agenciaAlquiler.setVehiculoDao(getDao(vista.getArchivo()));
        try {
            vista.mostrarMensaje("Se han guardado " + agenciaAlquiler.guardarVehiculos() + " vehiculos");
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void cargarVehiculos() {
        agenciaAlquiler.setVehiculoDao(getDao(vista.getArchivo()));
        try {
            int numVehiculos = agenciaAlquiler.cargarVehiculos();
            listarVehiculos();
            vista.mostrarMensaje("Se han cargado " + numVehiculos + " vehiculos");
        } catch (DaoException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void buscarVehiculo() {
        try {
            Matricula matricula = Matricula.valueOf(vista.getMatricula());
            Vehiculo v = agenciaAlquiler.consultarVehiculo(matricula);
            if (v != null) {
                vista.mostrarGrupo(v.getGrupo().toString());
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
                if (v instanceof Turismo) {
                    vista.mostrarPlazas(((Turismo) v).getPlazas());
                } else {
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
            } else {
                vista.mostrarMensaje("No existe un vehiculo con la matricula " + matricula);
                vista.limpiarCampos();
            }
        } catch (MatriculaException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void buscarVehiculoMasBarato() {
        if (agenciaAlquiler.getFlota().size() > 0) {
            Vehiculo v = agenciaAlquiler.getVehiculoMasBarato();
            if (v != null) {
                vista.mostrarMatricula(v.getMatricula().toString());
                vista.mostrarGrupo(v.getGrupo().toString());
                String tipo = v.getClass().getSimpleName().toUpperCase();
                vista.mostrarTipo(tipo);
                if (v instanceof Turismo) {
                    vista.mostrarPlazas(((Turismo) v).getPlazas());
                } else {
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            }
        } else {
            vista.mostrarMensaje("No hay vehiculos en la tabla");
        }
    }

    public void buscarVehiculoMasCaro() {
        if (agenciaAlquiler.getFlota().size() > 0) {
            Vehiculo v = Collections.max(agenciaAlquiler.getFlota(), new ComparadorPrecio());
            if (v != null) {
                vista.mostrarMatricula(v.getMatricula().toString());
                vista.mostrarGrupo(v.getGrupo().toString());
                String tipo = v.getClass().getSimpleName().toUpperCase();
                vista.mostrarTipo(tipo);
                if (v instanceof Turismo) {
                    vista.mostrarPlazas(((Turismo) v).getPlazas());
                } else {
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            }
        } else {
            vista.mostrarMensaje("No hay vehiculos en la tabla");
        }
    }

    /*public void consultarPrecio() {
        try {
            Matricula matricula = Matricula.valueOf(vista.getEntradaExamen());
            Vehiculo v = agenciaAlquiler.consultarVehiculo(matricula);
            if (v !=null) {
                v.getPrecioAlquiler();
            }
        } catch (MatriculaException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }*/
    public void calcularNumeroTurismos() {
        
        int contador=0;
        for (Vehiculo v: agenciaAlquiler.getFlota()){
            if (v instanceof Turismo){
                contador ++;
            }
        }
        vista.mostrarMensaje(Integer.toString(contador));
    }

    public void calcularCapacidadMedia() {
        
        float media=0;
        float capacidad=0;
        

    }

    public void mostrarTurismoMasPlazas() {

    }

    private static VehiculoDao getDao(String fichero) {
        VehiculoDao vd = null;
        int posPunto = fichero.lastIndexOf(".") + 1;
        String extension = fichero.substring(posPunto);
        switch (extension) {
            case "csv":
                vd = new VehiculoDaoCsv(fichero);
                break;
            case "obj":
                vd = new VehiculoDaoObj(fichero);
                break;
            case "json":
                vd = new VehiculoDaoJson(fichero);
                break;
            case "xml":
                vd = new VehiculoDaoXml(fichero);
        }
        return vd;
    }

    public void consultarPrecio() {
        String matricula = vista.getEntradaExamen();
        List<Vehiculo>lista;
        lista=agenciaAlquiler.getFlota();
        try {
            Matricula m = new Matricula(matricula);
            Vehiculo v = agenciaAlquiler.consultarVehiculo(m);
            boolean encontrado=false;
            for(int i=0; i<lista.size() && !encontrado;i++){
                if(lista.get(i).getMatricula().equals(v.getMatricula())){
                    vista.mostrarMensaje("El precio de alquiler es = " + lista.get(i).getPrecioAlquiler());
                    encontrado=true;
                }
            }

        } catch (MatriculaException ex) {
            vista.mostrarMensaje("Error en formato de matrícula");
        } catch (NullPointerException npe){
            vista.mostrarMensaje("La matrícula introducida no se corresponde con ningún vehículo");
        }
    }

}
