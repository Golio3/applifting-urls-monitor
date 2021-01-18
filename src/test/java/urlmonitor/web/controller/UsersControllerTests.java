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

public class UsersControllerTests {

    @Mock
    UsersRepository usersRepository;

    @Mock
    private UsersService usersService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    UsersController usersController;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void usersForm() {
        Assert.assertEquals(usersController.usersForm(model), "urlmonitor/users");
    }

    @Test
    public void addUser() {
        Assert.assertEquals(usersController.addUser(model), "urlmonitor/editUser");
    }

    @Test
    public void editUser() {
        Assert.assertEquals(usersController.editUser(0, model), "urlmonitor/editUser");
    }

    @Test
    public void saveUser() {
        UsersModel user = new UsersModel();
        user.setUsername("test");
        user.setPassword("pas");
        user.setRepeatPassword("no_pas");

        Assert.assertEquals(usersController.saveUser(user, bindingResult), "urlmonitor/editUser");
    }

    @Test
    public void deleleUser() {
        Assert.assertEquals(usersController.deleleUser(0), "redirect:/urlmonitor/users");
    }
}
