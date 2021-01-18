package urlmonitor.web.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;

public class UsersServiceTests {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UsersService usersService;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUserWithValidate() {
        UsersModel user = new UsersModel();
        user.setUsername("Username");
        user.setPassword("password");
        user.setRepeatPassword("password");
        user.setEmail("test@test.com");

        Assert.assertTrue(usersService.saveUserWithValidate(user));
    }

    @Test
    public void saveUserWithValidateError() {
        UsersModel user = new UsersModel();
        user.setUsername("Username");
        user.setPassword("password");
        user.setRepeatPassword("not_password");
        user.setEmail("test@test.com");

        Assert.assertFalse(usersService.saveUserWithValidate(user));
    }
}
