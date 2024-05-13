package programacion;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class VeterinarioPrincipal {


    public static void main(String[] args) {

        Statement sentencia = null;
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/?user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sentencia = conn.createStatement();

        Creacion.crearBase(sentencia);

        //INSERT INTO ESPECIALIDADES (idEsp,nombreEsp) VALUES (1,'traumatologia');
        //INSERT INTO ESPECIALIDADES (idEsp,nombreEsp) VALUES (2,'general');
        //INSERT INTO ESPECIALIDADES (idEsp,nombreEsp) VALUES (3,'nutricion');

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        int op1 = 0;
        int op2 = 0;
        int op3 = 0;
        int op4 = 0;
        boolean salir = false;

        do {
            Menus.showOptions();
            opcion = sc.nextInt();
            switch (opcion) {

                case 1:
                    while (salir == false) {
                        Menus.showOptionsInsert();
                        op1 = sc.nextInt();
                        switch (op1) {
                            case 1: // Insertar mascota
                                Clinica.insertMasc(sentencia);
                                break;
                            case 2://Insertar veterinario
                                Clinica.insertVet(sentencia);
                                break;
                            case 3:
                                salir = true;
                                break;

                        }

                    }
                    salir = false;
                    break;
                case 2:
                    while (salir == false) {
                        Menus.showOptionsDelete();
                        op2 = sc.nextInt();
                        switch (op2) {
                            case 1: // Eliminar mascota
                                Clinica.deleteMasc(sentencia);
                                break;
                            case 2: // Eliminar veterinario
                                Clinica.deleteVet(sentencia);
                                break;
                            case 3:
                                salir = true;
                                break;

                        }

                    }
                    salir = false;
                    break;
                case 3:
                    while (salir == false) {
                        Menus.showOptionsQueries();
                        op3 = sc.nextInt();
                        switch (op3) {
                            case 1: //Mostrar mascotas de un veterinario
                                Clinica.showPetData(sentencia);
                                break;
                            case 2: //Mostrar veterinario de una mascota
                                Clinica.showVetFromMasc(sentencia);
                                break;
                            case 3: //Exportar a fichero los datos de veterinarios con salario alto
                               Clinica.exportVetHighSalary(sentencia);
                                break;
                            case 4:
                                salir = true;
                                break;

                        }

                    }
                    salir = false;
                    break;
                case 4:
                    while (salir == false) {
                        Menus.showOptionsModify();
                        op4 = sc.nextInt();
                        switch (op4) {
                            case 1: // Modificar salario de veterinario
                                Clinica.modifyVetSalary(sentencia);
                                break;
                            case 2: //Modificar veterinario asignado a mascota
                                Clinica.modifyVetMasc(sentencia);
                                break;
                            case 3:
                                salir = true;
                                break;

                        }

                    }
                    salir = false;
                    break;

            }


        } while (opcion != 5);


    }
}
