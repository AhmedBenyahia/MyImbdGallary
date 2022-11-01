package test.arelion.myimbdgallary.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.Actor;
import test.arelion.myimbdgallary.model.dto.ActorDTO;
import test.arelion.myimbdgallary.model.dto.MovieCastDTO;
import test.arelion.myimbdgallary.repository.ActorRepository;
import test.arelion.myimbdgallary.service.ActorService;
import test.arelion.myimbdgallary.service.MovieCastService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository repositoryActor;
    private final MovieCastService movieCastService;
    private final ModelMapper modelMapper;

    @Override
    public Page<ActorDTO> getAllActor(int page, int size, String name) {
        Page<Actor> actors;
        if (name == null || name.isBlank() || name.isEmpty()) {
            actors = repositoryActor.findAll(PageRequest.of(page, size, Sort.by("primaryName")));
        } else {
            actors = repositoryActor.findByPrimaryNameContainingIgnoreCase(name,
                    PageRequest.of(page, size, Sort.by("primaryName")));
        }
        return  new PageImpl<>(actors.getContent()
                .stream()
                .map(actor ->  modelMapper.map(actor, ActorDTO.class))
                .collect(Collectors.toList()),
                actors.getPageable(), actors.getTotalElements());

    }

    @Override
    public ActorDTO getActorById(String id) {
        Optional<Actor> opt = repositoryActor.findById(id);
        Actor actor;
        if (opt.isPresent()) {
            actor = opt.get();
        } else {
            throw new NoSuchElementException("Actor with this id is not found");
        }
        return modelMapper.map(actor, ActorDTO.class);
    }

    @Override
    public Page<MovieCastDTO> getActorAppearances(String id, int page, int size) {
        return movieCastService.getActorAppearances(id, page, size);
    }
}
