package FR.movie.controller;

import FR.movie.excepcion.RecursoNoEncontradoExcepcion;
import FR.movie.servicio.IPeliculaServicio;
import FR.movie.modelo.Pelicula;
import FR.movie.servicio.PeliculaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Spring puerto : 8080
React puerto : 3000
*/

@RestController
//http://localhost:8080/movie-app//
@RequestMapping("/movie-app")
@CrossOrigin(origins = "http://localhost:3000")
public class PeliculaControlador {
    private static final Logger logger = LoggerFactory.getLogger(PeliculaControlador.class);

    @Autowired
    private IPeliculaServicio peliculaServicio;

    //http://localhost:8080/movie-app/peliculas
    @GetMapping("/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        var peliculas = peliculaServicio.listarPeliculas();
        peliculas.forEach((pelicula -> logger.info(pelicula.toString())));
        return peliculas;
    }

    @PostMapping("/peliculas")
    public Pelicula agregarPelicula(@RequestBody Pelicula pelicula) {
        logger.info("Pelicula a agregar: " + pelicula);
        return peliculaServicio.guardarPelicula(pelicula);
    }

    @GetMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula>
    obtenerPeliculaPorId(@PathVariable Integer id) {
        Pelicula pelicula = peliculaServicio.buscarPeliculaPorId(id);
        if (pelicula == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        }
        return ResponseEntity.ok(pelicula);
    }

    @PutMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Integer id, @RequestBody Pelicula peliculaRecibido) {
        Pelicula pelicula = peliculaServicio.buscarPeliculaPorId(id);
        if (pelicula == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        pelicula.setTitulo(peliculaRecibido.getTitulo());
        pelicula.setGeneros(peliculaRecibido.getGeneros());
        pelicula.setUrl(peliculaRecibido.getUrl());
        pelicula.setImagen(peliculaRecibido.getImagen());
        peliculaServicio.guardarPelicula(pelicula);
        return ResponseEntity.ok(pelicula);
    }

    @DeleteMapping("/peliculas/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarPelicula(@PathVariable Integer id) {
        Pelicula pelicula = peliculaServicio.buscarPeliculaPorId(id);
        if (pelicula == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        peliculaServicio.eliminarPelicula(pelicula);
        // Json {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
