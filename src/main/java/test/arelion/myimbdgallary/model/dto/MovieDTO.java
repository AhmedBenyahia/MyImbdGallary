package test.arelion.myimbdgallary.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * The {@link MovieDTO dto } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@Data
public class MovieDTO {

    String id;

    @JsonProperty("title")
    String primaryTitle;

    @JsonProperty("year")
    @JsonFormat(pattern="yyyy")
    LocalDate startYear;

}
