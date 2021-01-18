package urlmonitor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.MonitoredEndpointsRepository;
import urlmonitor.web.service.MonitoringService;
import urlmonitor.web.service.UsersService;

@Controller
@RequestMapping("/urlmonitor")
public class MonitoringResultController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private MonitoredEndpointsRepository monitoredEndpointsRepository;

    @GetMapping("/results")
    public String monitoringResultForm(Model model, Authentication authentication) {
        UsersModel user = usersService.getActiveUser(authentication);
        if (user != null) {
            model.addAttribute("endpoints", user.getMonitoredEndpointList());
        }
        return "urlmonitor/results";
    }

    @GetMapping("/results/start/{id}")
    public String startMonitoring(@PathVariable Integer id) {
        MonitoredEndpointsModel endpoint = monitoredEndpointsRepository.findOne(id);
        monitoringService.startMonitoring(endpoint);
        return "redirect:/urlmonitor/results";
    }

    @GetMapping("/results/stop/{id}")
    public String stopMonitoring(@PathVariable Integer id) {
        monitoringService.stopMonitoring(id);
        return "redirect:/urlmonitor/results";
    }
}
