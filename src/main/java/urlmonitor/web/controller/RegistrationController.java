package urlmonitor.web.controller;

import org.springframework.validation.BindingResult;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import urlmonitor.web.service.UsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public String formRegistration(Model model) {
        UsersModel reg = new UsersModel();
        model.addAttribute("reg", reg);
        return "urlmonitor/registration";
    }

    @PostMapping()
    public String register(@Valid @ModelAttribute("reg") UsersModel reg, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !usersService.saveUserWithValidate(reg)) {
            return "urlmonitor/registration";
        } else {
            return "redirect:/login";
        }
    }
}
