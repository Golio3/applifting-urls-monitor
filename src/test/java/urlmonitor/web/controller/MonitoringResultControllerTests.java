package urlmonitor.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import urlmonitor.web.repository.MonitoredEndpointsRepository;
import urlmonitor.web.service.MonitoringService;
import urlmonitor.web.service.UsersService;

public class MonitoringResultControllerTests {

    @Mock
    private UsersService usersService;

    @Mock
    private MonitoringService monitoringService;

    @Mock
    private MonitoredEndpointsRepository monitoredEndpointsRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private Model model;

    @InjectMocks
    MonitoringResultController monitoringResultController;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void monitoringResultForm() {
        Assert.assertEquals(monitoringResultController.monitoringResultForm(model, authentication), "urlmonitor/results");
    }

    @Test
    public void startMonitoring() {
        Assert.assertEquals(monitoringResultController.startMonitoring(0), "redirect:/urlmonitor/results");
    }

    @Test
    public void stopMonitoring() {
        Assert.assertEquals(monitoringResultController.stopMonitoring(0), "redirect:/urlmonitor/results");
    }
}
