package urlmonitor.web.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.client.RestTemplate;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.repository.MonitoredEndpointsRepository;
import urlmonitor.web.repository.MonitoringResultRepository;

public class MonitoringServiceTests {

    @Mock
    private TaskScheduler taskScheduler;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MonitoringResultRepository monitoringResultRepository;

    @Mock
    private MonitoredEndpointsRepository monitoredEndpointsRepository;

    @InjectMocks
    MonitoringService monitoringService;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void startMonitoring() {
        MonitoredEndpointsModel endpoint = new MonitoredEndpointsModel();
        endpoint.setId(0);
        endpoint.setMonitoredInterval(10);
        endpoint.setUrl("Http://google123.com");

        monitoredEndpointsRepository.save(endpoint);

        monitoringService.startMonitoring(endpoint);

        Assert.assertTrue(true);
    }

    @Test
    public void stopMonitoring() {
        monitoringService.stopMonitoring(0);

        Assert.assertTrue(true);
    }
}
