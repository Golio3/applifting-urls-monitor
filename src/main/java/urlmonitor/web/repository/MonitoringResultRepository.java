package urlmonitor.web.repository;

import org.springframework.data.repository.CrudRepository;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.model.MonitoringResultModel;

import java.util.List;

public interface MonitoringResultRepository extends CrudRepository<MonitoringResultModel, Integer> {

    List<MonitoringResultModel> findByMonitoredEndpointOrderByLastCheckDate(MonitoredEndpointsModel endpoint);
}
