package es.sauces.agenciaalquiler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author Usuario
 */
public interface VehiculoDao {
    List<Vehiculo> listar() throws DaoException;

    /**
     *
     * @param vehiculos
     * @return
     * @throws DaoException
     */
    public int insertar(List<Vehiculo> vehiculos) throws DaoException;
}
