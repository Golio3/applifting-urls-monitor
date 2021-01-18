package urlmonitor.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.repository.MonitoredEndpointsRepository;
import urlmonitor.web.service.UsersService;

public class EndpointsControllerTests {

    @Mock
    private MonitoredEndpointsRepository monitoredEndpointsRepository;

    @Mock
    private UsersService usersService;

    @Mock
    private Authentication authentication;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    EndpointsController endpointsController;

    private final Integer ID = 0;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void endpointsForm() {
        Assert.assertEquals(endpointsController.endpointsForm(model, authentication), "urlmonitor/endpoints");
    }

    @Test
    public void addEndpoint() {
        Assert.assertEquals(endpointsController.addEndpoint(model, authentication), "urlmonitor/editEndpoint");
    }

    @Test
    public void editEndpoint() {
        Assert.assertEquals(endpointsController.editEndpoint(ID, model), "urlmonitor/editEndpoint");
    }

    @Test
    public void saveEndpoint() {
        MonitoredEndpointsModel endpoint = new MonitoredEndpointsModel();
        endpoint.setUrl("Http:/test.com");
        endpoint.setMonitoredInterval(10);
        endpoint.setName("test");
        Assert.assertEquals(endpointsController.saveEndpoint(endpoint, bindingResult), "redirect:/urlmonitor/endpoints");
    }

    @Test
    public void deleleEndpoint() {
        Assert.assertEquals(endpointsController.deleleEndpoint(ID), "redirect:/urlmonitor/endpoints");
    }
}
