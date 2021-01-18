package urlmonitor.web.repository;

import org.springframework.data.repository.CrudRepository;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.model.UsersModel;

import java.util.List;

public interface MonitoredEndpointsRepository extends CrudRepository<MonitoredEndpointsModel, Integer> {

    List<MonitoredEndpointsModel> findByUser(UsersModel user);
}
