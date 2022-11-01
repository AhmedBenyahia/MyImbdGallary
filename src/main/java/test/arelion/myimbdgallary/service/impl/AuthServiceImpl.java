package test.arelion.myimbdgallary.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.User;
import test.arelion.myimbdgallary.model.dto.UserDTO;
import test.arelion.myimbdgallary.repository.UserRepository;
import test.arelion.myimbdgallary.service.AuthService;
import test.arelion.myimbdgallary.util.CommonFunction;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO user) {
        // We can improve the solution here by adding validation for the fields.
        user.setApiKey(CommonFunction.generateApiKey(user.getPricingPlan().toUpperCase(Locale.ROOT)));
        return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getUserApiKey(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent() && user.get().getApiKey() != null) {
            return user.get().getApiKey();
        }
        return "";
    }
}
