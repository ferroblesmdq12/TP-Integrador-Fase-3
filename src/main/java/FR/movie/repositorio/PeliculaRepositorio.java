package FR.movie.repositorio;

import FR.movie.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PeliculaRepositorio  extends JpaRepository<Pelicula, Integer>{

}
