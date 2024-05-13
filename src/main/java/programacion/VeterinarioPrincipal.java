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
         conn= (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/?user=root&password=");
        }catch(SQLException e){
            e.printStackTrace();
        }
        sentencia=conn.createStatement();


        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            Menus.showOptions();
            opcion = sc.nextInt();


        } while (opcion != 5);


    }
}
