package urlmonitor.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.model.MonitoringResultModel;
import urlmonitor.web.repository.MonitoringResultRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class MonitoringService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MonitoringResultRepository monitoringResultRepository;

    private Map<Integer, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

    public void startMonitoring(MonitoredEndpointsModel endpoint) {
        if (scheduledFutureMap.get(endpoint.getId()) == null) {
            ScheduledFuture<?> scheduledFuture = taskScheduler.scheduleAtFixedRate(monitoring(endpoint),endpoint.getMonitoredInterval() * 1000);
            scheduledFutureMap.put(endpoint.getId(), scheduledFuture);
        }
    }

    public void stopMonitoring(Integer id) {
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(id);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            scheduledFutureMap.remove(id);
        }
    }

    private Runnable monitoring(MonitoredEndpointsModel endpoint) {
        return () -> {
            ResponseEntity<String> response = restTemplate.getForEntity(endpoint.getUrl(), String.class);
            MonitoringResultModel result = new MonitoringResultModel(endpoint, response.getStatusCodeValue(), response.getBody().substring(0, 255), new Date());
            monitoringResultRepository.save(result);
            deleteExtraResults(endpoint);
        };
    }

    private void deleteExtraResults(MonitoredEndpointsModel endpoint) {
        List<MonitoringResultModel> results = monitoringResultRepository.findByMonitoredEndpointOrderByLastCheckDate(endpoint);
        if (results.size() > 10) {
            monitoringResultRepository.delete(results.get(0));
        }
    }
}
