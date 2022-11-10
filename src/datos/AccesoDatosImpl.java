package datos;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import domain.Pelicula;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements AccesoDatos {
    final Logger logger = LoggerFactory.getLogger(AccesoDatosImpl.class);
    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        //Se crea el archivo
        File archive = new File(nombreArchivo);
        return archive.exists();
    }

    @Override
    public List<Pelicula> listar(String nombre) throws LecturaDatosEx {
        File archivo = new File(nombre);
        List<Pelicula> lista = new ArrayList<>();
        try {
            //Se intancia la clase con que se leara cada linea del archivo
            BufferedReader entradas = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = entradas.readLine();
            //Preguntamos si la linea que leimos es distinto a vacío
            while(linea!=null){
                lista.add(new Pelicula(linea));
                linea = entradas.readLine();
            }
            entradas.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("Error en al listar en AccesoDatosImpl LecturaDatosEx = {}", e.getMessage());
            throw new LecturaDatosEx("Excepción al listar peliculas: "+ e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Error al leer las lineas del archivo: "+ e.getMessage() );
        }
        return lista;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archive = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archive, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito información al archivo: "+ pelicula);
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Error al escribir la pelicula: "+ e.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File file = new File(nombreArchivo);
        String resultado = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea = null;
            linea = reader.readLine();
            int indice=1;
            while (linea!=null) {
                if(buscar != null && buscar.equalsIgnoreCase(linea)){
                    resultado= "Pelicula "+ linea + " encontrada en el indice: "+ indice;
                    break;
                }
                indice++;
                linea = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("Error en al listar en AccesoDatosImpl LecturaDatosEx = {}", e.getMessage());
            throw new LecturaDatosEx("Excepción al listar peliculas: "+ e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Error al leer las lineas del archivo: "+ e.getMessage() );
        }

        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File file = new File(nombreArchivo);
        try {
            PrintWriter salida= new PrintWriter(new FileWriter(file));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosEx("Error al crear el archivo: "+ e.getMessage() );
        }

    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File file = new File(nombreArchivo);
        if (!file.exists()) throw new AccesoDatosEx("Error el archivo no existe: " );
        file.delete();
        System.out.println("Se ha borrado el archivo");
    }
}
