package test.arelion.myimbdgallary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import test.arelion.myimbdgallary.model.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
    Page<Movie> findByPrimaryTitleContainingIgnoreCase(String primaryTitle, Pageable pageable);
}
