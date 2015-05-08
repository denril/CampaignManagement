package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.UserEntity;
import sepe.dto.UserDTO;

import javax.annotation.Nullable;


@Component
public final class UserToUserDTO implements Converter<UserEntity, UserDTO> {


    @Override
    @Nullable
    public UserDTO convert(@Nullable final UserEntity user) {
        UserDTO dto = null;
        if (user != null) {
            dto = new UserDTO(
                    user.getId(),
                    user.getIsAdmin(),
                    user.getEmail(),
                    user.getRole(),

                    user.getUsername(),
                    user.getPassword(),

                    user.getFirstname(),
                    user.getLastname()

            );
        }
        return dto;
    }


}
