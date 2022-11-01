package test.arelion.myimbdgallary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The {@link Movie } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    String id;
    String titleType;
    String primaryTitle;
    String originalTitle;
    Boolean isAdult;
    LocalDate startYear;
    LocalDate endYear;
    Integer runtimeMinutes;
    String genres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private Set<MovieCast> actors = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
