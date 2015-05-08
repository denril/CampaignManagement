package sepe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.*;
import sepe.dto.*;
import sepe.dto.converter.*;
import sepe.handlers.EmailExistsException;
import sepe.handlers.UsernameExistsException;
import sepe.repository.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public final class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserToUserDTO userDTOConverter;


    @Nullable
    public UserDTO findUser(@Nonnull final Long userId) {
        final UserEntity user = userRepository.findOne(userId);
        return userDTOConverter.convert(user);
    }

    @Nullable
    public UserDTO findUserByEmail(@Nonnull final String email) {
        final UserEntity user = userRepository.findByEmailEquals(email);
        return userDTOConverter.convert(user);
    }

    @Nullable
    public UserDTO findAdmin(@Nonnull final Long userId) {
        final UserEntity admin = userRepository.findOne(userId);
        return userDTOConverter.convert(admin);
    }

    @Nonnull
    public List<UserDTO> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map((UserEntity user) -> userDTOConverter.convert(user))
                .collect(toList());
    }

    public void createUser(
            @Nonnull final UserDTO userDTO
    ) throws EmailExistsException, UsernameExistsException {
        if (emailExist(userDTO.getEmail())) {
            throw new EmailExistsException("Υπάρχει ήδη λογαριασμός με αυτή τη διεύθυνση email: " + userDTO.getEmail());
        }
        if (usernameExist(userDTO.getUsername())) {
            throw new UsernameExistsException("Υπάρχει ήδη λογαριασμός με αυτό το username: " + userDTO.getUsername());
        }

        UserDTOToUser userConverter = new UserDTOToUser();
        UserEntity user = userConverter.convert(userDTO);

        if (user.getRole() == Constants.USER_TYPE.ADMIN.getCode())
            user.setIsAdmin(1);
        userRepository.save(user);
    }

    public void updateUser(@Nonnull final UserDTO userDTO) {
        updateUser(userDTO, null);
    }

    public void updateUser(
            @Nonnull final UserDTO userDTO,
            @Nullable final String newPassword
    ) {
        final UserEntity user = userRepository.findOne(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());

        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(encryptPassword(newPassword));
        }
        userRepository.save(user);
        refreshAuthenticatedUser();
    }


    public void refreshAuthenticatedUser() {
        final Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuthentication.getPrincipal() instanceof SpringUserDetails) {
            final SpringUserDetails currentPrincipal = (SpringUserDetails) currentAuthentication.getPrincipal();
            final UserEntity refreshedUser = userRepository.findOne(currentPrincipal.getUserDTO().getId());
            final UserDTO userDTO = userDTOConverter.convert(refreshedUser);


            final SpringUserDetails refreshedPrincipal = new SpringUserDetails(userDTO, userDTO.getId(), refreshedUser.getPassword());
            final Authentication newAuthentication = new UsernamePasswordAuthenticationToken(refreshedPrincipal, refreshedUser.getPassword(), currentPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else {
            System.out.println("The currently-authenticated principal is not an instance of type SecurityConfig.SpringUserDetails");
        }
    }

    public boolean verifyPassword(
            @Nonnull final UserDTO userDTO,
            @Nonnull final String password
    ) {
        final UserEntity user = userRepository.findOne(userDTO.getId());
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Nonnull
    public String encryptPassword(@Nonnull final String rawPassword) {
        final String salt = BCrypt.gensalt(10, new SecureRandom());
        return BCrypt.hashpw(rawPassword, salt);
    }

    @Nullable
    public String getPasswordHash(@Nonnull final UserDTO userDTO) {
        final UserEntity user = userRepository.findOne(userDTO.getId());
        return user.getPassword();
    }

    @Nullable
    public String getPasswordHashForNewUser(@Nonnull final UserDTO userDTO) {
        final UserEntity user = userRepository.findByEmailEquals(userDTO.getEmail());
        return user.getPassword();
    }

    public void changeUserPassword(final UserDTO userDTO, final String password) {
        final UserEntity user = userRepository.findOne(userDTO.getId());
        if (user != null) {
            user.setPassword(encryptPassword(password));
            userRepository.save(user);
        }
    }


    private boolean emailExist(final String email) {
        final UserEntity user = userRepository.findByEmailEquals(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean usernameExist(final String username) {
        final UserEntity user = userRepository.findByUsernameEquals(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
