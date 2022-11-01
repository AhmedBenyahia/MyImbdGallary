package test.arelion.myimbdgallary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@link User } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String username;
    private String password;

    // Comma separated list of roles
    private String roles;

    private String apiKey;
    private String pricingPlan;


}