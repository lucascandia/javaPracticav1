package CPJLaboratorio;
import negocio.CatalogoPeliculas;
import negocio.CatalogoPeliculasImpl;

import  java.util.Scanner;
// Contiene el menu que permite al usuario seleccionar la acción a
// ejecutar sobre el catálogo de peliculas
public class Main {

    public static void main(String[] args) {
        int opcion=-1;
        String opcionaux;
        Scanner lectura = new Scanner(System.in);
        CatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        while(opcion!=0){
            menu();
            opcion = Integer.parseInt(lectura.nextLine());
            switch (opcion){
                case 1:
                    catalogo.iniciarCatalogosPelicula();
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la pelicula a agregar: ");
                    opcionaux = lectura.nextLine();
                    catalogo.agregarPelicula(opcionaux);
                    break;
                case 3:
                    catalogo.listaPeliculas();
                    break;
                case 4:
                    System.out.println("Ingrese el nombre de la pelicula a buscar: ");
                    opcionaux = lectura.nextLine();
                    catalogo.buscarPelicula(opcionaux);
                    break;
                case 0:
                    System.out.println("Gracias!!");
                    break;
                default:
                    System.out.println("Numero incorrecto ingresado");
            }
        }

    }


    private static void menu() {
        System.out.println("Elije una opción: \n"+
        "1 - Iniciar o borrar catalogo de peliculas \n" +
        "2 - Agregar pelicula \n" +
        "3 - Listar peliculas \n" +
        "4 - Buscar peliculas \n" +
        "0 - salir");
    }
}