package sepe.dto;

import org.hibernate.validator.constraints.NotEmpty;
import sepe.handlers.PasswordMatches;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PasswordMatches
public final class UserDTO implements Serializable {

    private long id;

    public Integer isAdmin;
    public Integer role;


    @NotNull
    @NotEmpty
    public String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 6)
    public String password;

    //@NotNull
    //@NotEmpty
    public String matchingPassword;


    @NotNull
    @NotEmpty
    public String email;

    @NotNull
    @NotEmpty
    public String firstname;

    @NotNull
    @NotEmpty
    public String lastname;


    public UserDTO(
            @Nonnull final long id,

            final Integer isAdmin,
            @Nonnull final String email,
            @Nonnull final Integer role,

            final String username,
            final String password,

            final String firstname,
            final String lastname

    ) {
        this.id = id;

        this.isAdmin = isAdmin;
        this.email = email;
        this.role = role;

        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;

    }


    public UserDTO() {
    }

    ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(final Object other) {
        boolean equals = false;
        if (other instanceof UserDTO) {
            final UserDTO that = (UserDTO) other;
            equals = this.getId() == that.getId()
                    && this.getEmail().equals(that.getEmail())
            ;
        }
        return equals;
    }

    /*
    @Override
    public int hashCode() {
        final int idHash = (id == null) ? 0 : id.hashCode();

        final int emailHash = (email == null) ? 0 : email.hashCode();
        final int roleHash = (role == null) ? 0 : role.hashCode();

        return idHash + emailHash + roleHash;
    }
*/
}
