<<<<<<< Updated upstream:src/main/java/ies/sauces/agenciaalquiler/modelo/VehiculoDaoJson.java
package ies.sauces.agenciaalquiler.modelo;
=======
package es.sauces.agenciaalquiler.modelo;
>>>>>>> Stashed changes:src/main/java/ies/sauces/agenciaalquiler/modelo/modelo/VehiculoDaoJson.java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class VehiculoDaoJson implements VehiculoDao{
    
     private Path path;

    public VehiculoDaoJson(String path) {
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
         Type tipo = new TypeToken<List<Vehiculo>>() {
        }.getType();
        RuntimeTypeAdapterFactory<Vehiculo> empleadoAdapter= RuntimeTypeAdapterFactory.of(Vehiculo.class, "type");
        empleadoAdapter.registerSubtype(Turismo.class, "Turismo");
        empleadoAdapter.registerSubtype(Furgoneta.class, "Furgoneta");
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        Gson gson = builder.create();
        List<Vehiculo> listado;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            listado = gson.fromJson(br, tipo);
        }catch(JsonSyntaxException | JsonIOException jse){
            throw new DaoException("Formato Incorrecto");
        }catch (IOException ex) {
            throw new DaoException(ex.toString());
        }
        return listado;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
         java.lang.reflect.Type tipo = new com.google.gson.reflect.TypeToken<List<Vehiculo>>() {
        }.getType();
        RuntimeTypeAdapterFactory<Vehiculo> empleadoAdapter = RuntimeTypeAdapterFactory.of(Vehiculo.class, "type");
        empleadoAdapter.registerSubtype(Turismo.class, "Turismo");
        empleadoAdapter.registerSubtype(Furgoneta.class, "Furgoneta");
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        Gson gson = builder.create();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            gson.toJson(vehiculos, tipo, bw);
        } catch (IOException ex) {
            throw new DaoException(ex.getMessage());
        }
        return vehiculos.size();
    }
}
