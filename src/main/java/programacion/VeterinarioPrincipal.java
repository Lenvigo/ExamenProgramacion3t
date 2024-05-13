package programacion;
import java.util.Scanner;

public class VeterinarioPrincipal {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            Menus.showOptions();
            opcion = sc.nextInt();


        } while (opcion != 5);


    }
}
