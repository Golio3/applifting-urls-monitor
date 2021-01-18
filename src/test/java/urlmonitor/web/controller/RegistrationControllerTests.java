package urlmonitor.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;
import urlmonitor.web.service.UsersService;

public class RegistrationControllerTests {

    @Mock
    UsersRepository usersRepository;

    @Mock
    private UsersService usersService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    RegistrationController registrationController;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void formRegistration() {
        Assert.assertEquals(registrationController.formRegistration(model), "urlmonitor/registration");
    }

    @Test
    public void register() {
        UsersModel user = new UsersModel();
        user.setUsername("test");
        user.setPassword("pas");
        user.setRepeatPassword("no_pas");

        Assert.assertEquals(registrationController.register(user, bindingResult), "urlmonitor/registration");
    }
}
