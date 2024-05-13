package programacion;

import org.mariadb.jdbc.Statement;

import java.sql.SQLException;
import java.util.Scanner;

class Clinica {
    private static Scanner sc = new Scanner(System.in);

    public static void insertVet(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("introduce dni veterinario");
        String dniVet = sc.next();
        System.out.println("introduce nombre veterinario");
        String nomVet = sc.next();
        System.out.println("introduce direccion vet");
        String dirVet = sc.next();
        System.out.println("introduce fecha de contratacion (yyyy-mm-dd)");
        String fechaCVet = sc.next();
        System.out.println("introduce edad veterinario");
        int age = sc.nextInt();
        System.out.println("introduce especialidad veterinario");
        int espVet = sc.nextInt();
        System.out.println("introduce sueldo vet");
        float salVet = sc.nextFloat();

        String query = "INSERT INTO VETERINARIOS (dni,nombreVet,edad,direccion,sueldo,esp,fechaCont) VALUES ('" + dniVet + "','" + nomVet + "','" + age + "','" + dirVet + "','" + salVet + "','" + espVet + "','" + fechaCVet + "');";


        try {
            int i = sentencia.executeUpdate(query);
            if(i>0){
                System.out.println("veterinario introducido");
            }else{
                System.out.println("veterinario no introducido");
            }

        } catch (SQLException e) {
            System.out.println("error en la carga de datos");;
        }

    }


    public static void insertMasc(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("introduce nombre mascota");
        String nomMas = sc.next();
        System.out.println("introduce especie");
        String espMasc = sc.next();
        System.out.println("introduce raza");
        String razMas = sc.next();
        System.out.println("introduce fecha de nacimiento (yyyy-mm-dd)");
        String feNac = sc.next();
        System.out.println("introduce fecha dalta en plataforma (yyyy-mm-dd)");
        String feAlt = sc.next();
        System.out.println("introduce dni veterinario");
        String dVet = sc.next();


        String query = "INSERT INTO MASCOTAS (nombreMasc,especie,raza,fechaNac,fechaAlta,veterinario) VALUES ('" + nomMas + "','" + espMasc + "','" + razMas + "','" + feNac + "','" + feAlt + "','" + dVet + "');";


        try {
            int i = sentencia.executeUpdate(query);
            if(i>0){
                System.out.println("mascota introducida");
            }else{
                System.out.println("mascota no introducida");
            }

        } catch (SQLException e) {
            System.out.println("error en la carga de datos");;
        }

    }

    public static void deleteVet(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("introduce dni veterinario");
        String dniVet = sc.next();

        String query = "DELETE FROM VETERINARIOS  WHERE dni= '" + dniVet + "';";

        try {
            int i = sentencia.executeUpdate(query);
            if(i>0){
                System.out.println("veterinario ELIMINADO");
            }else{
                System.out.println("veterinario no ELIMINADO");
            }

        } catch (SQLException e) {
            System.out.println("error en la elimiacion de datos");;
        }


    }

    public static void deleteMasc(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("introduce nombre mascota");
        String nomMas = sc.next();
        System.out.println("introduce especie");
        String espMasc = sc.next();

        String query = "DELETE FROM MASCOTAS WHERE  nombreMasc= '" + nomMas + "' AND especie='" + espMasc + "';";

        try {
            int i = sentencia.executeUpdate(query);
            if(i>0){
                System.out.println("mascota ELIMINADA");
            }else{
                System.out.println("mascota no ELIMINAD");
            }

        } catch (SQLException e) {
            System.out.println("error en la ELIMINACION de datos");;
        }
    }

    public static void showPetData(Statement sentencia) {

    }


    public static void showVetFromMasc(Statement sentencia) {


    }

    // Se debe devolver la ruta al fichero exportado
    public static String exportVetHighSalary(Statement sentencia) {

        return null;
    }

    public static void modifyVetSalary(Statement sentencia) {
    }

    public static void modifyVetMasc(Statement sentencia) {
    }
}

