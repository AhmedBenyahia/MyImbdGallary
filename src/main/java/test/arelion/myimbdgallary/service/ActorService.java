package test.arelion.myimbdgallary.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.dto.ActorDTO;
import test.arelion.myimbdgallary.model.dto.MovieCastDTO;

@Service
public interface ActorService {


    Page<ActorDTO> getAllActor(int page, int size, String name);

    ActorDTO getActorById(String id);

    Page<MovieCastDTO> getActorAppearances(String id, int page, int size);
}
