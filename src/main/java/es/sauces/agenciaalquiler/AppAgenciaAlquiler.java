package es.sauces.agenciaalquiler;

import java.util.List;
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
        int opcion;
        Scanner teclado = new Scanner(System.in);
        List<Vehiculo> flota;

        do {
            System.out.println("1.Crear turismo.");
            System.out.println("2.Crear furgoneta.");
            System.out.println("3.Consultar vehiculo.");
            System.out.println("4.Eliminar vehiculo.");
            System.out.println("5.Listar vehiculo por precio.");
            System.out.println("6.Listar turismos.");
            System.out.println("7.Listar furgonetas.");
            System.out.println("8.Consultar alquiler mas barato.");
            System.out.println("0.Salir.");
            System.out.println("Introduzca opcion:");
            while (!teclado.hasNextInt()) {
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    String matricula,
                     letraGrupo;
                    Grupo grupo = null;
                    int plazas;
                    System.out.println("1.Crear turismo.");
                    System.out.println("Introduzca matricula: ");
                    matricula = teclado.nextLine();
                    System.out.println("Introduzca grupo(A,B,C): ");
                    letraGrupo = teclado.nextLine();
                    System.out.println("Introduzca numero de plazas:");
                    plazas = teclado.nextInt();
                    teclado.nextLine();
                    if (aa.incluirVehiculo(new Turismo(matricula, Grupo.valueOf(letraGrupo), plazas))) {
                        System.out.println("Turismo registrado.");
                    } else {
                        System.out.println("No se ha podido incluir el turismo.");
                    }
                    System.out.println("-------------------------------------");
                    break;

                case 2:
                    grupo = null;
                    int capacidad;
                    System.out.println("2.Crear furgoneta.");
                    System.out.println("Introduzca matricula: ");
                    matricula = teclado.nextLine();
                    System.out.println("Introduzca grupo(A,B,C): ");
                    letraGrupo = teclado.nextLine();
                    System.out.println("Introduzca capacidad:");
                    capacidad = teclado.nextInt();
                    teclado.nextLine();
                    if (aa.incluirVehiculo(new Furgoneta(matricula, Grupo.valueOf(letraGrupo), capacidad))) {
                        System.out.println("Furgoneta registrada.");
                    } else {
                        System.out.println("No se ha podido incluir la furgoneta.");
                    }
                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    System.out.println("3.Consultar vehiculo.");
                    System.out.println("Introduzca matricula para consultar vehiculo");
                    matricula = teclado.nextLine();
                    System.out.println(aa.consultarVehiculo(matricula));
                    break;
                case 4:
                    System.out.println("4.Eliminar vehiculo.");
                    System.out.println("Introduzca matricula para eliminar vehiculo");
                    matricula = teclado.nextLine();
                    System.out.println("Esta seguro de eliminar este vehiculo");
                    String opcion2;
                    opcion2 = teclado.nextLine();
                    if (opcion2.equalsIgnoreCase("si")) {
                        aa.eliminarVehiculo(aa.consultarVehiculo(matricula));
                        System.out.println("Vehiculo eliminado.");
                    } else {
                        System.out.println("Ha decidido no eliminar el vehiculo.");
                    }
                    break;
                case 5:
                    System.out.println("5.Listar vehiculo por precio.");
                    for (Vehiculo vehiculo : aa.listarVehiculosPorPrecio()) {
                        System.out.println(vehiculo.toString());
                    }

                    break;
                case 6:
                    System.out.println("6.Listar turismos.");
                    for (Vehiculo vehiculo : aa.getFlota()) {
                        if (vehiculo.getClass().equals(Turismo.class)) {
                            System.out.println(vehiculo.toString());
                        }
                    }
                    break;
                case 7:
                    System.out.println("7.Listar furgonetas.");
                    for (Vehiculo vehiculo : aa.getFlota()) {
                        if (vehiculo.getClass().equals(Furgoneta.class)) {
                            System.out.println(vehiculo.toString());
                        }
                    }
                    break;
                case 8:
                    System.out.println("8.Consultar alquiler mas barato.");
                    Vehiculo vehiculo = aa.getVehiculoMasBarato();
                    System.out.println(vehiculo + " mas barato= " + vehiculo.getPrecioAlquiler());
                    System.out.println("-------------------------------------");
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

}
