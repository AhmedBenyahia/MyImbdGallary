package test.arelion.myimbdgallary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import test.arelion.myimbdgallary.model.MovieCast;
import test.arelion.myimbdgallary.model.MovieCastId;

public interface MovieCastRepository extends PagingAndSortingRepository<MovieCast, MovieCastId> {
    Page<MovieCast> findByActorId(Pageable pageable, String id);
}
