package test.arelion.myimbdgallary.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * The {@link ActorDTO dto } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@Data
public class ActorDTO {
    String id;

    @JsonProperty("name")
    String primaryName;

//    private Set<MovieDTO> bestMovies = new LinkedHashSet<>();
//    private Set<MovieCastDTO> movieCasts = new LinkedHashSet<>();
}
