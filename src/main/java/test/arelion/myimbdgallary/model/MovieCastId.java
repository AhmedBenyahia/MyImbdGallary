package test.arelion.myimbdgallary.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MovieCastId implements Serializable {

    String movieId;
    Integer ordering;
    String actorId;
}
