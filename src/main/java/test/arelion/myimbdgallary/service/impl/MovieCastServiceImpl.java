package test.arelion.myimbdgallary.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.MovieCast;
import test.arelion.myimbdgallary.model.dto.MovieCastDTO;
import test.arelion.myimbdgallary.repository.MovieCastRepository;
import test.arelion.myimbdgallary.service.MovieCastService;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieCastServiceImpl implements MovieCastService {

    private final MovieCastRepository movieCastRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<MovieCastDTO> getActorAppearances(String id, int page, int size) {
        Page<MovieCast> movieCast = movieCastRepository.findByActorId(PageRequest.of(page, size, Sort.by("movie.primaryTitle")), id);
        return  new PageImpl<>(movieCast.getContent()
                .stream()
                .map(movieC -> {
                    MovieCastDTO movie = modelMapper.map(movieC, MovieCastDTO.class);
                    movie.setMovieName(movieC.getMovie().getPrimaryTitle());
                    return movie;
                })
                .collect(Collectors.toList()),
                movieCast.getPageable(), movieCast.getTotalElements());
    }
}
