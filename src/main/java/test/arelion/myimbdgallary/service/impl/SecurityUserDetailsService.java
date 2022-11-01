package test.arelion.myimbdgallary.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.dto.UserDTO;
import test.arelion.myimbdgallary.repository.UserRepository;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new UserDTO(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present")));
    }

}
