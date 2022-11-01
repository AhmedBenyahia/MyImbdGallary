package test.arelion.myimbdgallary.service;

import org.springframework.data.domain.Page;
import test.arelion.myimbdgallary.model.dto.MovieCastDTO;

public interface MovieCastService {
    Page<MovieCastDTO> getActorAppearances(String id, int page, int size);
}
