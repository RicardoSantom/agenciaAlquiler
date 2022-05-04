package es.sauces.agenciaalquiler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Ricardo Santiago Tomé
 */
public class AppAgenciaAlquiler {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AgenciaAlquiler aa = new AgenciaAlquiler();
        int opcion, plazas = 0;
        Scanner teclado = new Scanner(System.in);
        List<Vehiculo> flota;
        String entrada = null;
        Grupo grupo = null;
        Matricula matricula = null;
        Properties Propiedades=new Properties();
        Propiedades.setProperty("ultimaconexion",LocalDateTime.now().toString());

        do {
            System.out.println("1.Crear turismo.");
            System.out.println("2.Crear furgoneta.");
            System.out.println("3.Consultar vehiculo.");
            System.out.println("4.Eliminar vehiculo.");
            System.out.println("5.Listar vehiculo por grupo.");
            System.out.println("6.Listar vehiculo por precio.");
            System.out.println("7.Listar turismos.");
            System.out.println("8.Listar furgonetas.");
            System.out.println("9.Consultar alquiler mas barato.");
            System.out.println("10.Guardar vehiculos.");
            System.out.println("11.Cargar vehiculos.");
            System.out.println("0.Salir.");
            System.out.println("Introduzca opcion:");
            while (!teclado.hasNextInt()) {
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1: 
                    try {
                    System.out.println("1.Crear turismo.");
                    System.out.println("Introduzca matricula: ");
                    entrada = teclado.nextLine();
                    matricula = Matricula.valueOf(entrada);
                    System.out.println("Introduzca grupo(A,B,C): ");
                    entrada = teclado.nextLine();
                    grupo = Grupo.valueOf(entrada);
                    System.out.println("Introduzca numero de plazas:");
                    plazas = teclado.nextInt();
                    teclado.nextLine();
                    if (aa.incluirVehiculo(new Turismo(matricula, grupo, plazas))) {
                        System.out.println("Turismo registrado.");
                    } else {
                        System.out.println("No se ha podido incluir el turismo.");
                    }
                    System.out.println("-------------------------------------");
                } catch (MatriculaException ex) {
                    System.out.println(ex.getMessage());
                } catch (InputMismatchException ime) {
                    System.out.println("Debe ser un entero.");
                    teclado.nextLine();
                } catch (IllegalArgumentException iae) {
                    System.out.println("Debe introducir uno de los siguients valores:" + Arrays.toString(Grupo.values()));
                }
                break;

                case 2:
                    try {
                    int capacidad;
                    System.out.println("2.Crear furgoneta.");
                    System.out.println("Introduzca matricula: ");
                    entrada = teclado.nextLine();
                    matricula = Matricula.valueOf(entrada);
                    System.out.println("Introduzca grupo(A,B,C): ");
                    System.out.println(Arrays.toString(Grupo.values()));
                    entrada = teclado.nextLine();
                    grupo = Grupo.valueOf(entrada);
                    System.out.println("Introduzca capacidad:");
                    capacidad = teclado.nextInt();
                    teclado.nextLine();
                    if (aa.incluirVehiculo(new Furgoneta(matricula, grupo, capacidad))) {
                        System.out.println("Furgoneta registrada.");
                    } else {
                        System.out.println("No se ha podido incluir la furgoneta.");
                    }
                    System.out.println("-------------------------------------");
                } catch (MatriculaException ex) {
                    System.out.println(ex.getMessage());
                } catch (InputMismatchException ime) {
                    System.out.println("debe ser un entero.");
                } catch (IllegalArgumentException iae) {
                    System.out.println("Debe introducir uno de los siguients valores:" + Arrays.toString(Grupo.values()));
                }
                break;
                case 3:
                    try {
                    System.out.println("3.Consultar vehiculo.");
                    System.out.println("Introduzca matricula para consultar vehiculo");
                    entrada = teclado.nextLine();
                    matricula = Matricula.valueOf(entrada);
                } catch (MatriculaException ex) {
                    System.out.println(ex.getMessage());
                }

                if (aa.consultarVehiculo(matricula) != null) {
                    System.out.println(aa.consultarVehiculo(matricula));
                } else {
                    System.out.println("No existe el vehiculo");
                }
                break;
                case 4:
                    try {
                    System.out.println("4.Eliminar vehiculo.");
                    System.out.println("Introduzca matricula para eliminar vehiculo");
                    entrada = teclado.nextLine();
                    matricula = Matricula.valueOf(entrada);
                    System.out.println("Esta seguro de eliminar este vehiculo(SI/NO)");
                    String opcion2;
                    opcion2 = teclado.nextLine();
                    if (opcion2.equalsIgnoreCase("si")) {
                        aa.eliminarVehiculo(aa.consultarVehiculo(matricula));
                        System.out.println("Vehiculo eliminado.");
                    } else {
                        System.out.println("Ha decidido no eliminar el vehiculo.");
                    }
                } catch (MatriculaException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 5:
                    try {
                    System.out.println("5.Listar vehiculo por grupo.");
                    boolean correcto;
                    do {
                        correcto = false;
                        System.out.println("Introduzca grupo a buscar. " + Arrays.toString(Grupo.values()));
                        entrada = teclado.nextLine();
                        grupo = Grupo.valueOf(entrada);
                        for (Grupo g : Grupo.values()) {
                            if (grupo.equals(g)) {
                                correcto = true;
                            }
                        }
                    } while (!correcto);
                } catch (IllegalArgumentException iae) {
                    System.out.println("Debe introducir uno de los siguients valores:" + Arrays.toString(Grupo.values()));
                }
                for (Vehiculo v : aa.listarVehiculos(grupo)) {
                    System.out.println(v);
                }
                break;
                case 6:
                    System.out.println("6.Listar vehiculo por precio.");
                    for (Vehiculo vehiculo : aa.listarVehiculosPorPrecio()) {
                        System.out.println(vehiculo.toString());
                    }

                    break;
                case 7:
                    System.out.println("7.Listar turismos.");
                    for (Vehiculo vehiculo : aa.getFlota()) {
                        if (vehiculo.getClass().equals(Turismo.class)) {
                            System.out.println(vehiculo.toString());
                        }
                    }
                    break;
                case 8:
                    System.out.println("8.Listar furgonetas.");
                    for (Vehiculo vehiculo : aa.getFlota()) {
                        if (vehiculo instanceof Turismo) {
                            System.out.println(vehiculo);
                        }
                    }
                    break;
                case 9:
                    System.out.println("9.Consultar alquiler mas barato.");
                    Vehiculo vehiculo = aa.getVehiculoMasBarato();
                    System.out.println(vehiculo + " mas barato= " + vehiculo.getPrecioAlquiler());
                    System.out.println("-------------------------------------");
                    break;
                case 10:
                    System.out.println("10.Guardar vehiculos.");
                    System.out.println("Introduce el nombre del fichero a guardar");
                    String archivo = teclado.nextLine();
                    aa.setVehiculoDao(getDao(archivo));
                    try {
                        int n = aa.guardarVehiculos();
                        System.out.println("Se han guardado " + n + " vehiculos");
                    } catch (DaoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 11:
                    System.out.println("11.Cargar vehiculos.");
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    System.out.println("-------------------------------------");

            }

        } while (opcion != 0);

    }
    private static VehiculoDao getDao(String archivo) {
        VehiculoDao vd = null;
        int posicion = archivo.lastIndexOf(".") + 1;
        String extension;
        extension = archivo.substring(posicion);
        switch (extension) {
            case "csv":
                vd = new VehiculoDaoCsv(archivo);
                break;
            /*case "obj":
                ed = new VehiculoDaoObj(archivo);
                break;
            case "xml":
                ed = new VehiculoDaoXml(archivo);
                break;
            case "json":
                ed = new VehiculoDaoJson(archivo);
                break;*/
        }
        return vd;
    }
}
