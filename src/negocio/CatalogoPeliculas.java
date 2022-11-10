package negocio;

// Contiene las operaciones necesarias de la aplicación CatalogoPeliculas
public interface CatalogoPeliculas {
    String NOMBRE_RECURSO= "pelicula.txt";
    public void agregarPelicula(String nombrePelicula);
    public void listaPeliculas();
    public void buscarPelicula(String buscar);
    public void iniciarCatalogosPelicula();
}
