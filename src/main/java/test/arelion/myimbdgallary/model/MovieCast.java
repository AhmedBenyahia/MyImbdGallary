package test.arelion.myimbdgallary.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MovieCast implements Serializable {

    @EmbeddedId
    MovieCastId id;

    String category;
    String job;
    String characters;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Actor.class)
    @MapsId("actorId")
    private Actor actor;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Movie.class)
    @MapsId("movieId")
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieCast movieCast = (MovieCast) o;
        return id != null && Objects.equals(id, movieCast.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

