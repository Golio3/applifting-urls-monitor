package urlmonitor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;
import urlmonitor.web.service.UsersService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/urlmonitor")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String usersForm(Model model) {
        List<UsersModel> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "urlmonitor/users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {
        UsersModel user = new UsersModel();
        model.addAttribute("user", user);
        return "urlmonitor/editUser";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        UsersModel user = usersRepository.findOne(id);
        if (user != null) {
            user.setPassword("");
            model.addAttribute("user", user);
        }
        return "urlmonitor/editUser";
    }

    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") UsersModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !usersService.saveUserWithValidate(user)) {
            return "urlmonitor/editUser";
        } else {
            return "redirect:/urlmonitor/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleleUser(@PathVariable Integer id) {
        usersRepository.delete(id);
        return "redirect:/urlmonitor/users";
    }
}
