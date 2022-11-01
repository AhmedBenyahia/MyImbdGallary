package test.arelion.myimbdgallary.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.arelion.myimbdgallary.exception.ActorNotFoundException;
import test.arelion.myimbdgallary.model.dto.ActorDTO;
import test.arelion.myimbdgallary.model.dto.MovieCastDTO;
import test.arelion.myimbdgallary.service.ActorService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
public class ActorController {

    private final ActorService actorService;

    /**
     * Gets all Actors with the possibility of filtering by name.
     * The API accept three parameters
     *
     * @param page the page number. Required field
     * @param size the number of element per page. Required field.
     * @param name the name of a actor to filter with.  Optional field.
     * @return The list of actord
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping()
    public List<ActorDTO> getAll(@RequestParam(name = "page",  defaultValue = "0") int page,
                                     @RequestParam(name = "page_size", defaultValue = "10") int size,
                                     @RequestParam(name = "name", required = false) String name) {
        Page<ActorDTO> resultPage = actorService.getAllActor(page, size, name);
        if (page > resultPage.getTotalPages()) {
            throw new ActorNotFoundException(HttpStatus.NOT_FOUND.value(), "The Page Requested Do Not Exist!");
        }
        return resultPage.getContent();
    }

    /**
     * Get actor by ID.
     *
     * @param id the id of the actor requested
     * @return the actor requested
     */
    @GetMapping("/{id}")
    public ActorDTO getActor(@PathVariable String id) {
        return actorService.getActorById(id);
    }


    @GetMapping("/{id}/appearances")
    public List<MovieCastDTO> getActorAppearances(@PathVariable String id,
                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int size) {
        return actorService.getActorAppearances(id, page, size).getContent();
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
     * Handle actor not found exception response entity.
     *
     * @param e the exception object
     * @return the response entity
     */
    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<String> handleActorNotFoundException(ActorNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
