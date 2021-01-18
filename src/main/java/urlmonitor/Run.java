package urlmonitor;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;
import urlmonitor.web.service.UsersService;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class Run extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Run.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    TaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public ApplicationRunner initializer(UsersRepository usersRepository, UsersService usersService) {
        List<UsersModel> userAdmins = usersRepository.findByRoles(usersService.ROLE_ADMIN);
        if (userAdmins.size() == 0) {
            UsersModel initUserAdmin = new UsersModel();
            initUserAdmin.setUsername("admin");
            initUserAdmin.setRoles(usersService.ROLE_ADMIN);
            initUserAdmin.setActive(1);
            initUserAdmin.setPassword("admin");
            initUserAdmin.setRepeatPassword("admin");
            usersService.saveUserWithValidate(initUserAdmin);
        }
        return args -> {};
    }
}
