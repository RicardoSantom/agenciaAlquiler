/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenciaalquiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/

/**
 *
 * @author Usuario
 */
public class VehiculoDaoCsv implements VehiculoDao{
    private Path path;

    public VehiculoDaoCsv(String path) {
        this.path = Paths.get(path);
    }
    

    @Override
    public List<Vehiculo> listar() throws DaoException {
        List<Vehiculo> listado=new ArrayList<>();
        String linea,tokens[];
        Vehiculo vehiculo;
        int plazas;
        float capacidad;
        Matricula matricula;
        Grupo grupo;
        try(BufferedReader entrada = Files.newBufferedReader(path)){
            linea=entrada.readLine();
            while(linea!=null){
                tokens=linea.split(",");
                if(tokens[0].equalsIgnoreCase("Turismo")){
                    /*matricula, grupo, plazas*/
                    matricula=Matricula.valueOf(tokens[1]);
                    grupo=Grupo.valueOf(tokens[2]);
                    plazas=Integer.parseInt(tokens[3]);
                    vehiculo=new Turismo(matricula, grupo, plazas);
                } else {
                    matricula=Matricula.valueOf(tokens[1]);
                    grupo=Grupo.valueOf(tokens[2]);
                    capacidad=Float.parseFloat(tokens[3]);
                    vehiculo=new Furgoneta(matricula, grupo, capacidad);
                }
                listado.add(vehiculo);
                linea=entrada.readLine();
            }
        } catch (MatriculaException | IOException ex) {
            throw new DaoException(ex.toString());
        }catch (Exception ex){
            throw new DaoException("Formato incorrecto");
        }
        
        return listado;
    }

    @Override
    public int insertar(List<Vehiculo> listado) throws DaoException {
        try (BufferedWriter salida = Files.newBufferedWriter(path)) {
            for(Vehiculo e : listado){
                salida.write(e.getClass().getSimpleName()+","+e.toString());
                salida.newLine();
            }
        } catch (IOException ex) {
            throw new DaoException(ex.getMessage());
        }catch(Exception ex){
            throw new DaoException("Formato incorrecto");
        }
        
        return listado.size();
    }
}
