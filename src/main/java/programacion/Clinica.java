package programacion;

import org.mariadb.jdbc.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class Clinica {
    private static Scanner sc = new Scanner(System.in);

    public static void insertVet(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el dni del veterinario");
        String dniVet = sc.next();
        System.out.println("Introduce el nombre del veterinario");
        String nomVet = sc.next();
        System.out.println("Introduce la direccion del veterinario");
        String dirVet = sc.next();
        System.out.println("Introduce la fecha de contratacion del veterinario (yyyy-mm-dd)");
        String fechaCVet = sc.next();
        System.out.println("Introduce la edad  del veterinario");
        int age = sc.nextInt();
        System.out.println("Introduce la especialidad del veterinario");
        int espVet = sc.nextInt();
        System.out.println("Introduce el sueldo del veterinario");
        float salVet = sc.nextFloat();

        String query = "INSERT INTO VETERINARIOS (dni,nombreVet,edad,direccion,sueldo,esp,fechaCont) VALUES ('" + dniVet + "','" + nomVet + "','" + age + "','" + dirVet + "','" + salVet + "','" + espVet + "','" + fechaCVet + "');";


        try {
            int i = sentencia.executeUpdate(query);
            if (i > 0) {
                System.out.println("Veterinario introducido correctamente en la BD");
            } else {
                System.out.println("Veterinario no introducido");
            }

        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
            ;
        }

    }


    public static void insertMasc(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la mascota");
        String nomMas = sc.next();
        System.out.println("Introduce la especie");
        String espMasc = sc.next();
        System.out.println("Introduce la raza");
        String razMas = sc.next();
        System.out.println("Introduce su fecha de nacimiento (yyyy-mm-dd)");
        String feNac = sc.next();
        System.out.println("Introduce la fecha de alta en plataforma (yyyy-mm-dd)");
        String feAlt = sc.next();
        System.out.println("Introduce el dni  del veterinario asignado");
        String dVet = sc.next();


        String query = "INSERT INTO MASCOTAS (nombreMasc,especie,raza,fechaNac,fechaAlta,veterinario) VALUES ('" + nomMas + "','" + espMasc + "','" + razMas + "','" + feNac + "','" + feAlt + "','" + dVet + "');";


        try {
            int i = sentencia.executeUpdate(query);
            if (i > 0) {
                System.out.println("Mascota introducida correctamente en la BD");
            } else {
                System.out.println("Mascota no introducida el la BD");
            }

        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
            ;
        }

    }

    public static void deleteVet(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el dni del  veterinario");
        String dniVet = sc.next();

        String query = "DELETE FROM VETERINARIOS  WHERE dni= '" + dniVet + "';";

        try {
            int i = sentencia.executeUpdate(query);
            if (i > 0) {
                System.out.println("Veterinario eliminado correctamente");
            } else {
                System.out.println("Veterinario no eliminado");
            }

        } catch (SQLException e) {
            System.out.println("Error en la elimiacion de datos");
            ;
        }


    }

    public static void deleteMasc(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la mascota");
        String nomMas = sc.next();
        System.out.println("Introduce la especie");
        String espMasc = sc.next();

        String query = "DELETE FROM MASCOTAS WHERE  nombreMasc= '" + nomMas + "' AND especie='" + espMasc + "';";

        try {
            int i = sentencia.executeUpdate(query);
            if (i > 0) {
                System.out.println("Mascota eliminada correctamente");
            } else {
                System.out.println("Mascota no eliminada");
            }

        } catch (SQLException e) {
            System.out.println("Error en la ELIMINACION de datos");
            ;
        }
    }

    public static void showPetData(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce nombre veterinario");
        String nomVet = sc.next();
        String query0 = "SELECT * FROM VETERINARIOS  WHERE nombreVet='" + nomVet + "';";
        try {
            ResultSet res = sentencia.executeQuery(query0);
            if (res.next()) {
                String dniV = res.getString("dni");
                String query = "SELECT * FROM MASCOTAS WHERE veterinario='" + dniV + "' ;";
                try {
                    ResultSet r = sentencia.executeQuery(query);
                    while (r.next()) {
                        String nomMasc = r.getString("nombreMasc");
                        String espMasc = r.getString("especie");
                        String razMasc = r.getString("raza");
                        String fnMasc = r.getString("fechaNac");
                        String faMasc = r.getString("fechaAlta");
                        int idMasc = r.getInt("idMascota");

                        System.out.println("Mascota nombre: " + nomMasc + ". Especie: " + espMasc + ". Raza: " + razMasc + ". Identicador: " + idMasc);
                        System.out.println("Fecha Nacimiento: " + fnMasc + ". Fecha alta: " + faMasc);
                    }
                } catch (SQLException e) {
                    System.out.println("Error en la carga de datos");
                }
            } else {
                System.out.println("Veterinario no encontrado en la bd");
            }
        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
        }
    }


    public static void showVetFromMasc(Statement sentencia) {
        // mostrar mascotas que lleven mas de 4 años en la bd
        sc = new Scanner(System.in);
        String query = "SELECT * FROM MASCOTAS";
        try {
            ResultSet r = sentencia.executeQuery(query);
            while (r.next()) {
                String nomMas = r.getNString("nombreMasc");
                String fnA = r.getString("fechaAlta");
                String mVet = r.getString("veterinario");
                LocalDate alta = LocalDate.parse(fnA);
                LocalDate ahora = LocalDate.now();
                int dif = Period.between(alta, ahora).getYears();
                if (Math.abs(dif) > 4) {
                    try {
                        String query1 = "SELECT * FROM VETERINARIOS WHERE dni='" + mVet + "';";
                        ResultSet rs = sentencia.executeQuery(query1);
                        while (rs.next()) {
                            String nomVet = rs.getString("nombreVet");
                            String dirVet = rs.getString("direccion");
                            int esp = rs.getInt("esp");
                            System.out.println("Mascota:" + nomMas + ", antiguedad: " + dif + "años. Veterinario: " + nomVet + " de " + dirVet + " identificador especialidad: " + esp);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error en la carga de datos de veterinario");
                    }

                }
            }

        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
        }


    }

    // Se debe devolver la ruta al fichero exportado
    public static String exportVetHighSalary(Statement sentencia) {
        String ruta = "VetHighSalary.txt";
        File file = new File(ruta);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String query = "SELECT * FROM VETERINARIOS WHERE  sueldo=(SELECT MAX(sueldo) FROM VETERINARIOS)";
            ResultSet r = sentencia.executeQuery(query);
            while (r.next()) {
                String nom = r.getString("nombreVet");
                float salVet = r.getFloat("sueldo");
                System.out.println("Datos añadidos en fichero " + ruta + ": " + nom + " " + salVet);
                writer.write(" Nombre: " + nom + ". Su salario es de " + salVet + " euros.");
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Error en los ficheros");
        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
        }

        return ruta;
    }

    public static void modifyVetSalary(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del veterinario");
        String nomVet = sc.next();
        System.out.println("Introduce nuevo salario para el veterinario");
        float salVet = sc.nextFloat();

        String query = "UPDATE VETERINARIOS set sueldo='" + salVet + "'  WHERE nombreVet='" + nomVet + "';";

        try {
            sentencia.executeUpdate(query);
            System.out.println("Salario modificado");
        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
            e.printStackTrace();
        }

    }

    public static void modifyVetMasc(Statement sentencia) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la mascota");
        String nomMas = sc.next();
        System.out.println("Introduce la especie");
        String espMasc = sc.next();
        System.out.println("Introduce dni del nuevo veterinario");
        String idVete = sc.next();

        String query = "UPDATE MASCOTAS SET veterinario='" + idVete + "' WHERE nombreMasc='" + nomMas + "' AND especie='" + espMasc + "' ;";
        try {
            sentencia.executeUpdate(query);
            System.out.println("Datos modificados");
        } catch (SQLException e) {
            System.out.println("Error en la carga de datos");
        }

    }
}

