package test.arelion.myimbdgallary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The {@link Actor } class.
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
public class Actor {
    @Id
    String id;
    String primaryName;
    LocalDate birthYear;
    LocalDate deathYear;
    String primaryProfession;



    /**
     * List of the movies an actor is famous for.
     * The relationship table 'actor_best_movie' is managed by hibernate.
     * We are using a LinkedHashSet because it is an ordered and sorted collection of HashSet.
     * This enables us to keep the original order or apply a custom sort if we want.
     */
    @ManyToMany
    @JoinTable(name = "actor_best_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> bestMovies = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private Set<MovieCast> movieCasts = new LinkedHashSet<>();



}
