package negocio;


import datos.AccesoDatos;
import datos.AccesoDatosImpl;
import domain.Pelicula;
import excepciones.AccesoDatosEx;
import excepciones.LecturaDatosEx;

//Contiene las implementaciones de las operaciones
//necesarias de la aplicación CatalogoPeliculas
public class CatalogoPeliculasImpl implements CatalogoPeliculas {

    private static final String PELICULA_NOT_FOUND = "Pelicula no encontrada";
    private final AccesoDatos datos;
    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl();
    }


    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexo;
        try {
            anexo = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexo);
            System.out.println("Se agrego la pelicula correctamente");
        } catch (AccesoDatosEx e) {
            System.out.println("No existe el archivo");
            e.printStackTrace();
        }
    }

    @Override
    public void listaPeliculas() {
        try {
            //datos.listar(NOMBRE_RECURSO).forEach(System.out::println);
            for(Pelicula pelicula : datos.listar(NOMBRE_RECURSO)){
                System.out.println("Pelicula: "+pelicula);
            }
        } catch (LecturaDatosEx e) {
            System.out.println("Error al listar la pelicula");
            e.printStackTrace(System.out);
        }

    }

    @Override
    public void buscarPelicula(String buscar) {
        try {
            // Si devulve null no existe la pelicula en el archivo
            String encontrado = datos.buscar(NOMBRE_RECURSO, buscar);
            System.out.println(encontrado == null ? PELICULA_NOT_FOUND : encontrado);
        } catch (AccesoDatosEx e) {
            System.out.println("Error al acceso de datos");
            e.printStackTrace();
        }

    }

    @Override
    public void iniciarCatalogosPelicula() {
        try {
            if(datos.existe(NOMBRE_RECURSO))
                datos.borrar(NOMBRE_RECURSO);
            datos.crear(NOMBRE_RECURSO);
        } catch (AccesoDatosEx e) {
            System.out.println("No se pudo crear el archivo en inicialCatalogosPelicula");
            e.printStackTrace();
        }

    }
}
