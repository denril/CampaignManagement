package sepe.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sepe.dto.UserDTO;

@Component
public class UserValidator implements Validator {
    @Autowired
    EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "message.firstName", "FirstName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "message.lastName", "LastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "message.username", "UserName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.email", "Email is required.");
        UserDTO o = (UserDTO) obj;
        if (EmailValidator.validateEmail(o.getEmail()) == false) {
            errors.rejectValue("email", "message.email", "email is not good.");
        }
    }

}
