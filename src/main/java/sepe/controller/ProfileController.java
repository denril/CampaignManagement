package sepe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.dto.UserDTO;
import sepe.handlers.EmailExistsException;
import sepe.handlers.UserValidator;
import sepe.handlers.UsernameExistsException;
import sepe.service.CurrentUserDetailsService;
import sepe.service.MailService;
import sepe.service.UserService;

import javax.annotation.Nonnull;
import java.security.SecureRandom;
import java.util.Random;

import static sepe.config.Constants.StringIsNullOrEmpty;

@Controller
public final class ProfileController extends AbstractController {
    private static final Random RANDOM = new SecureRandom();
    private final UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MailService mailService;

    @Autowired
    public ProfileController(@Nonnull final UserService userService) {
        this.userService = userService;
    }






    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    @Nonnull
    public final String register(
            @Nonnull final Model model

    ) throws Exception {
        final UserDTO user = new UserDTO();
        user.setUsername("");
        user.setEmail("");
        user.setFirstname("");
        user.setLastname("");

        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = {"/register/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUser(
            @Nonnull
            @RequestParam(value = "password")
            final String password,

            @Nonnull
            @ModelAttribute("user")
            UserDTO userDTO,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model
    ) throws Exception {
        SpringUserDetails currentUserEntity = CurrentUserDetailsService.getCurrentUserEntity();
        UserDTO adminDTO = currentUserEntity.getUserDTO();
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");
            return "register";
        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");
            return "register";
        } else {
            if (!password.isEmpty()) {
                try {
                    userService.createUser(userDTO);
                } catch (EmailExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return register(model);
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return register(model);
                }
                UserDTO updatedUser = userService.findUserByEmail(userDTO.getEmail());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    //userService.createCitizen(updatedUser.getId());

                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    //userService.createCitizen(updatedUser.getId());
                }

                try {
                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                } catch (Exception ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return register(model);
                }

                // Update the user in the active Spring Security session
                final String passwordHash = userService.getPasswordHashForNewUser(adminDTO);
                final SpringUserDetails newUserDetails = new SpringUserDetails(adminDTO, adminDTO.getId(), passwordHash);
                final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, null);
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }
        }


        return accountSaved(userDTO,adminDTO,model);
    }

    @RequestMapping(value = {"/accountSaved"}, method = RequestMethod.GET)
    @Nonnull
    public final String accountSaved(
            @Nonnull UserDTO userDTO, @Nonnull UserDTO adminDTO, @Nonnull final Model model

    ) throws Exception {
        model.addAttribute("user", userDTO);
        model.addAttribute("admin", adminDTO);
        return "accountSaved";
    }

    @RequestMapping(value = {"/passwordreset"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUser(
            @Nonnull
            @RequestParam(value = "email")
            final String email,

            @Nonnull
            final Model model
    ) throws Exception {
        if (StringIsNullOrEmpty(email)) {
            model.addAttribute("profileSaveError", "Το email δεν είναι έγκυρο. Προσπαθήστε ξανά!");
            return Constants.PASSWORD_TEMPLATE;
        }
        final UserDTO user = userService.findUserByEmail(email);
        if (user == null) {
            model.addAttribute("profileSaveError", "Το email δεν είναι έγκυρο. Προσπαθήστε ξανά!");
            return Constants.PASSWORD_TEMPLATE;
        } else {
            model.addAttribute("profileSaveError", "Το email είναι έγκυρο. Σας έχει σταλεί με Εμαιλ ο νέος σας κωδικός!");
            String newpassword = generateRandomPassword(8);
            userService.changeUserPassword(user, newpassword);
            mailService.passwordReminder(user.getEmail(), newpassword);
            return Constants.PASSWORD_TEMPLATE;
        }
    }


    public static String generateRandomPassword(int length) {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String pw = "";
        for (int i = 0; i < length; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }
}
