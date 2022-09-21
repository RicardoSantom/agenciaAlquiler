<<<<<<< Updated upstream:src/main/java/ies/sauces/agenciaalquiler/modelo/VehiculoDaoObj.java
package ies.sauces.agenciaalquiler.modelo;
=======
package es.sauces.agenciaalquiler.modelo;
>>>>>>> Stashed changes:src/main/java/ies/sauces/agenciaalquiler/modelo/modelo/VehiculoDaoObj.java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class VehiculoDaoObj implements VehiculoDao{
    
    private Path path;
    
     public VehiculoDaoObj(String path) {
        this.path = Paths.get(path);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public List<Vehiculo> listar() throws DaoException {
        List<Vehiculo> listado=new ArrayList<>();
        try(InputStream is=Files.newInputStream(path);
            ObjectInputStream entrada=new ObjectInputStream(is)){
            while(is.available()>0){
                listado.add((Vehiculo)entrada.readObject());
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ex.getMessage());
        }
        return listado;    
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        try(ObjectOutputStream salida=new ObjectOutputStream(Files.newOutputStream(path))){
           for(Vehiculo v:vehiculos){
               salida.writeObject(v);
           }
        } catch (IOException ex) { 
       throw new DaoException(ex.getMessage());
       } 
        return vehiculos.size();
    }
    
}
