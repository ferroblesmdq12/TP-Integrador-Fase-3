package FR.movie.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPelicula;
    String titulo;
    String generos;
    String url;
    String imagen;
}
