package sepe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.CharacterEncodingFilter;
import sepe.domain.UserEntity;
import sepe.dto.UserDTO;
import sepe.dto.converter.UserToUserDTO;
import sepe.repository.UserRepository;
import sepe.service.CurrentUserDetailsService;

import javax.annotation.Nonnull;

@Configuration
@EnableWebMvcSecurity
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public final class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
        @Override
        public void onApplicationEvent(final InteractiveAuthenticationSuccessEvent event) {

            // Retrieve the user
            final SpringUserDetails userDetails = (SpringUserDetails) event.getAuthentication().getPrincipal();
            final UserDTO userDTO = userDetails.getUserDTO();
            final UserEntity user = userRepository.findOne(userDTO.getId());
            Logger logger = LoggerFactory.getLogger("SecurityConfig");
            logger.debug("Just logged in user " + user.getEmail() + " with id " + user.getId());
        }
    }


    @Autowired
    UserRepository userRepository;


    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Bean
    public LoginListener loginListener() {
        return new LoginListener();
    }

    @Autowired
    SecurityEvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);
        http.csrf().disable();
        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        http.authorizeRequests()
                //Public Access (not post, not put, read only)
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/static/bootstrap/**").permitAll()
                .antMatchers("/static/bootstrap-datepicker/**").permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/passwordreset/**").permitAll()
                .antMatchers("/password/**").permitAll()
                .antMatchers("/anonymous").permitAll()
                .antMatchers("/static/userapp/store/shared/**").permitAll()
                .antMatchers("/register/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
        ;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(buildDaoAuthenticationProvider());
    }

    @Nonnull
    private DaoAuthenticationProvider buildDaoAuthenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }


}
