package FR.movie.servicio;

import FR.movie.modelo.Pelicula;
import java.util.List;
public interface IPeliculaServicio {
    public List<Pelicula> listarPeliculas();

    public Pelicula buscarPeliculaPorId(Integer idPelicula);

    public Pelicula guardarPelicula(Pelicula pelicula);

    public void eliminarPelicula(Pelicula pelicula);
}
