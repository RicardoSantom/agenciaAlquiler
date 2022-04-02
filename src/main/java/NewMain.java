
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        /*try(BufferedReader entrada= Files.newBufferedReader(Paths.get("properties.properties"))){
            propiedades.load(entrada);
            System.out.println(propiedades.get("clave1"));
            System.out.println(propiedades.get("clave2"));
       
        }*/

 /*properties.setProperty("nuevaClave","nuevoValor");
        Propiedades.setProperty("fecha",LocalDateTime.now().toString());
        Propiedades.setProperty("hora",LocalTime.now().toString());
        try (BufferedWriter salida = Files.newBufferedWriter(Paths.get("properties.properties"))) {
            properties.store(salida, "comentario");
        }*/
 
        try (InputStream fichero = ClassLoader.getSystemClassLoader().getResourceAsStream("Propiedades.properties");) {
            properties.load(fichero);
            String clave1 = properties.getProperty("clave1");
            System.out.println(clave1);
        } 
    }
}
