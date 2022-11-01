package test.arelion.myimbdgallary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import test.arelion.myimbdgallary.model.Actor;
import test.arelion.myimbdgallary.model.Movie;

public interface ActorRepository extends JpaRepository<Actor, String> {
    Page<Actor> findByPrimaryNameContainingIgnoreCase(String name, PageRequest of);
}
