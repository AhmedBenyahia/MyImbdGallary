package test.arelion.myimbdgallary.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import test.arelion.myimbdgallary.exception.MovieNotFoundException;
import test.arelion.myimbdgallary.model.dto.MovieDTO;
import test.arelion.myimbdgallary.service.MovieService;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * {@link MovieController } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * Gets all Movies with the possibility of filtering by name.
     * The API accept three parameters
     *
     * @param page the page number. Required field
     * @param size the number of element per page. Required field.
     * @param name the name of a movie to filter with.  Optional field.
     * @return The list of movied
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping()
    public List<MovieDTO> getAll(@RequestParam(name = "page",  defaultValue = "0") int page,
                                 @RequestParam(name = "page_size", defaultValue = "10") int size,
                                 @RequestParam(name = "name", required = false) String name) {
        Page<MovieDTO> resultPage = movieService.getAllMovie(page, size, name);
        if (page > resultPage.getTotalPages()) {
            throw new MovieNotFoundException(HttpStatus.NOT_FOUND.value(), "The Page Requested Do Not Exist!");
        }
        return resultPage.getContent();
    }

    /**
     * Get movie by ID.
     *
     * @param id the id of the movie requested
     * @return the movie requested
     */
    @GetMapping("/{id}")
    public MovieDTO getMovies(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    /**
     * Handle no such element exception response entity.
     *
     * @param e the exception object
     * @return the response entity
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle movie not found exception response entity.
     *
     * @param e the exception object
     * @return the response entity
     */
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
