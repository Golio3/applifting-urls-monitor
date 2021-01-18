package urlmonitor.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class HomeControllerTests {

    @InjectMocks
    HomeController homeController;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void home() {
        Assert.assertEquals(homeController.home(), "index");
    }
}
