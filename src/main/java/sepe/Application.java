package sepe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.WebApplicationInitializer;
import sepe.handlers.*;

import java.util.Arrays;



@Configuration
//@ComponentScan({"sepe.config.*","sepe.controller.*","sepe.domain.*","sepe.dto.*","sepe.handlers.*","sepe.repository.*","sepe.service.*"})
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static Class<Application> applicationClass = Application.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(applicationClass, args);
        RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);
        restConfiguration.setReturnBodyOnCreate(true);

        Logger logger = LoggerFactory.getLogger("Application");
        logger.debug("Let's inspect the beans provided by Spring Boot:");

        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            logger.debug(beanName);
        }

        //test(ctx);
    }

    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    /*
    @Override
    protected void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", new PeopleValidator());
    }

    @Bean
    @Override
    public ValidatingRepositoryEventListener validatingRepositoryEventListener() {
        ValidatingRepositoryEventListener listener = new ValidatingRepositoryEventListener();
        configureValidatingRepositoryEventListener(listener);
        listener.addValidator("afterCreate", validator());
        listener.addValidator("beforeCreate", validator());
        return listener;
    }
    */

/*
    public static void test(ApplicationContext ctx){
        String TEDBG = new String("~~~~~~~~~~~~~~~~~~> ");

        System.out.println(TEDBG);


        try {

            EmployeeDisputeRepository repository = ctx.getBean(EmployeeDisputeRepository.class);
            TEmployeeDispute empDisp = new TEmployeeDispute();

            if(false) {
                // Create
                empDisp.setProtNo(5555);
                //repository.save(empDisp);
            }else{
                // Update
                    empDisp = repository.findOne(new Long(431));

                //empDisp.setProtNo(5556);
            }

            ////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////

            TEmployeeDisputeReason empDispReason = new TEmployeeDisputeReason();

            empDispReason.setComments("bla bla");
            empDispReason.setReasonId(5);

            empDispReason.settEmployeeDispute(empDisp);
            empDisp.gettEmployeeDisputeReason().add(empDispReason);

            repository.save(empDisp);

        }catch(Exception ex){
            System.out.println(TEDBG + ex.toString());
        }

    }
*/
}
