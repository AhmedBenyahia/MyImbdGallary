package test.arelion.myimbdgallary.service;

import org.springframework.data.domain.Page;
import test.arelion.myimbdgallary.model.Movie;
import test.arelion.myimbdgallary.model.dto.MovieDTO;

public interface MovieService {
    Page<MovieDTO> getAllMovie(int page, int size, String name);

    MovieDTO getMovieById(String id);

}
