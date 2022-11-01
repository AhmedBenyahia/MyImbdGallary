package test.arelion.myimbdgallary.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.Movie;
import test.arelion.myimbdgallary.model.dto.MovieDTO;
import test.arelion.myimbdgallary.repository.MovieRepository;
import test.arelion.myimbdgallary.service.MovieService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repositoryMovie;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository repositoryMovie, ModelMapper modelMapper) {
        this.repositoryMovie = repositoryMovie;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<MovieDTO> getAllMovie(int page, int size, String name) {
        Page<Movie> movies;
        if (name == null || name.isBlank() || name.isEmpty()) {
            movies = repositoryMovie.findAll(PageRequest.of(page, size, Sort.by("primaryTitle")));
        } else {
            movies = repositoryMovie.findByPrimaryTitleContainingIgnoreCase(name,
                    PageRequest.of(page, size, Sort.by("primaryTitle")));
        }
        return new PageImpl<>(movies.getContent()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()),
                movies.getPageable(), movies.getTotalElements());

    }

    @Override
    public MovieDTO getMovieById(String id) {
        Optional<Movie> opt = repositoryMovie.findById(id);
        Movie movie;
        if (opt.isPresent()) {
            movie = opt.get();
        }
        else {
            throw new NoSuchElementException("Movie with this id is not found");
        }
        return modelMapper.map(movie, MovieDTO.class);
    }
}
