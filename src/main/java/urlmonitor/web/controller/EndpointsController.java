package urlmonitor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urlmonitor.web.model.MonitoredEndpointsModel;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.MonitoredEndpointsRepository;
import urlmonitor.web.service.UsersService;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/urlmonitor")
public class EndpointsController {

    @Autowired
    private MonitoredEndpointsRepository monitoredEndpointsRepository;

    @Autowired
    private UsersService usersService;

    @GetMapping("/endpoints")
    public String endpointsForm(Model model, Authentication authentication) {
        UsersModel user = usersService.getActiveUser(authentication);
        if (user != null) {
            model.addAttribute("endpoints", user.getMonitoredEndpointList());
        }
        return "urlmonitor/endpoints";
    }

    @GetMapping("/endpoints/add")
    public String addEndpoint(Model model, Authentication authentication) {
        UsersModel user = usersService.getActiveUser(authentication);
        MonitoredEndpointsModel endpoint = new MonitoredEndpointsModel();
        endpoint.setUser(user);
        model.addAttribute("endpoint", endpoint);
        return "urlmonitor/editEndpoint";
    }

    @GetMapping("/endpoints/edit/{id}")
    public String editEndpoint(@PathVariable Integer id, Model model) {
        MonitoredEndpointsModel endpoint = monitoredEndpointsRepository.findOne(id);
        model.addAttribute("endpoint", endpoint);
        return "urlmonitor/editEndpoint";
    }

    @PostMapping("/endpoints/save")
    public String saveEndpoint(@Valid @ModelAttribute("endpoint") MonitoredEndpointsModel endpoint, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "urlmonitor/editEndpoint";
        }

        endpoint.setCreationDate(new Date());
        monitoredEndpointsRepository.save(endpoint);
        return "redirect:/urlmonitor/endpoints";
    }

    @GetMapping("/endpoints/delete/{id}")
    public String deleleEndpoint(@PathVariable Integer id) {
        monitoredEndpointsRepository.delete(id);
        return "redirect:/urlmonitor/endpoints";
    }
}
