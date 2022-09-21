package ies.sauces.agenciaalquiler.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*import java.util.ListIterator;*/
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class AgenciaAlquiler {

    private String nombre;
    private List<Vehiculo> flota;
    private VehiculoDao vehiculoDao;

    /**
     *
     */
    public AgenciaAlquiler() {
        flota = new ArrayList<>();
    }

    public VehiculoDao getVehiculoDao() {
        return vehiculoDao;
    }

    public void setVehiculoDao(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public List<Vehiculo> getFlota() {
        return flota;
    }

    /**
     *
     * @param flota
     */
    public void setFlota(List<Vehiculo> flota) {
        this.flota = flota;
    }

    /**
     *
     * @param vehiculo
     * @return
     */
    public boolean incluirVehiculo(Vehiculo vehiculo) {

        boolean incluido = true;
        for (int i = 0; i < flota.size() && incluido == true; i++) {
            if (flota.get(i).getMatricula().equals(vehiculo.getMatricula())) {
                incluido = false;
            }
        }

        if (incluido) {
            flota.add(vehiculo);
        }

        return incluido;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public Vehiculo consultarVehiculo(Matricula matricula) {
        /*if (flota.containsKey(matricula)) {
            return flota.get(matricula);
        }
        return null;*/
        for (Vehiculo v : flota) {
            if (matricula.equals(v.getMatricula())) {
                return v;
            }
        }
        return null;
    }

    /**
     *
     * @param vehiculo
     * @return
     */
    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        /*boolean salida = false;

        if (vehiculo != null) {
            salida = flota.remove(vehiculo);
        }
        return salida;*/
        if (vehiculo != null) {
            return flota.remove(vehiculo);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public List<Vehiculo> listarVehiculosPorPrecio() {
        Collections.sort(flota, new ComparadorPrecio());
        return flota;
    }

    //El método que sigue está comentado a la espera de futuras implementaciones
    public List<Vehiculo> listarTodosVehiculos() {

        return flota;
    }

    /**
     *
     * @param grupo
     * @return
     */
    public List<Vehiculo> listarVehiculos(Grupo grupo) {
        List<Vehiculo> salida = new ArrayList<>();
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.getGrupo().equals(grupo)) {
                salida.add(vehiculo);
            }
        }
        return salida;
    }

    /**
     *
     * @return
     */
    public Vehiculo getVehiculoMasBarato() {
        return Collections.min(flota, new ComparadorPrecio());
        /*Vehiculo barato;
        barato = Collections.min(flota, new ComparadorPrecio());
        return barato;*/
    }

    public int guardarVehiculos() throws DaoException {
        if (vehiculoDao == null) {
            throw new DaoException("No se ha podido encontrar la extension del fichero");
        }
        return vehiculoDao.insertar(new ArrayList<>(flota));

        /*int n = 0;
        if (vehiculoDao == null) {
            throw new DaoException("No se ha establecido un archivo");
        }
        n = vehiculoDao.insertar(listarTodosVehiculos());
        return n;*/
    }

    public int cargarVehiculos() throws DaoException {
        int n = 0;
        if (vehiculoDao == null) {
            throw new DaoException("No existe el archivo");
        }

        List<Vehiculo> listado = vehiculoDao.listar();
        for (Vehiculo vehiculo : listado) {
            if (incluirVehiculo(vehiculo)) {
                n++;
            }
        }
        return n;
    }

}
