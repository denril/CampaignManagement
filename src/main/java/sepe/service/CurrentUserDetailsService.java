package sepe.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;
import sepe.domain.UserEntity;
import sepe.dto.UserDTO;
import sepe.dto.converter.UserToUserDTO;
import sepe.repository.UserRepository;


@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserToUserDTO userDTOConverter;


    @Override
    public SpringUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with username={}", username.replaceFirst("@.*", "@***"));
        if (userRepository == null) {
            System.out.println("NULL USER REPOSITORY");
        }
        final UserEntity user = userRepository.findByUsernameEquals(username);
        final UserDTO userDTO = userDTOConverter.convert(user);

        return (user == null) ? null : new SpringUserDetails(userDTO, userDTO.getId(), user.getPassword());
    }

    public static SpringUserDetails getCurrentUserEntity() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SpringUserDetails) {
            return (SpringUserDetails) authentication.getPrincipal();
        } else {
            return null;
        }
    }


}