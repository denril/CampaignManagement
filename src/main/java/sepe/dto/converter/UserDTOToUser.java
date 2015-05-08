package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import sepe.domain.UserEntity;
import sepe.dto.UserDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;


@Component
public final class UserDTOToUser implements Converter<UserDTO, UserEntity> {

    @Override
    @Nullable
    public UserEntity convert(@Nullable final UserDTO userDTO) {
        UserEntity user = null;
        if (userDTO != null) {
            user = new UserEntity(
                    userDTO.getId(),
                    0, // 0 user is not Admin, 1 user is Admin
                    userDTO.getRole(),
                    userDTO.getEmail(),
                    userDTO.getUsername(),
                    encryptPassword(userDTO.getPassword()),
                    userDTO.getFirstname(),
                    userDTO.getLastname()
            );
        }
        return user;
    }

    @Nonnull
    public String encryptPassword(@Nonnull final String rawPassword) {
        final String salt = BCrypt.gensalt(10, new SecureRandom());
        return BCrypt.hashpw(rawPassword, salt);
    }
}
