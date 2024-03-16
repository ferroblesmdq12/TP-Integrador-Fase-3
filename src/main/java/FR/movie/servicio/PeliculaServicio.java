package FR.movie.servicio;

import FR.movie.modelo.Pelicula;
import FR.movie.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServicio implements IPeliculaServicio{
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepositorio.findAll();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        Pelicula pelicula = peliculaRepositorio.findById(idPelicula).orElse(null);
        return pelicula;
    }

    @Override
    public Pelicula guardarPelicula(Pelicula pelicula) {
        return peliculaRepositorio.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaRepositorio.delete(pelicula);

    }
}
