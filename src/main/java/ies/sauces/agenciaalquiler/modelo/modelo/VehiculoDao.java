package ies.sauces.agenciaalquiler.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/main/java/ies/sauces/agenciaalquiler/modelo/VehiculoDao.java

=======
package es.sauces.agenciaalquiler.modelo;
>>>>>>> Stashed changes:src/main/java/ies/sauces/agenciaalquiler/modelo/modelo/VehiculoDao.java

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
