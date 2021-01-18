package urlmonitor.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class LoginControllerTests {

    @InjectMocks
    LoginController loginController;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login() {
        Assert.assertEquals(loginController.login(), "urlmonitor/login");
    }

    @Test
    public void accessDenied() {
        Assert.assertEquals(loginController.accessDenied(), "urlmonitor/error/access-denied");
    }
}
