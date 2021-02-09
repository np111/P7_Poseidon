package com.poseidon.app.controllers;

import com.poseidon.app.model.User;
import com.poseidon.app.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@Scope("singleton")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", filterOut(userService.list()));
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }

        userService.create(filterIn(user));
        model.addAttribute("users", filterOut(userService.list()));
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", filterOut(userService.read(id)));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        userService.update(id, filterIn(user));
        model.addAttribute("users", filterOut(userService.list()));
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.delete(id);
        model.addAttribute("users", filterOut(userService.list()));
        return "redirect:/user/list";
    }

    private User filterIn(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    private User filterOut(User user) {
        if (user.getPassword() != null) {
            user.setPassword("");
        }
        return user;
    }

    private List<User> filterOut(List<User> ret) {
        if (ret != null) {
            ret.forEach(this::filterOut);
        }
        return ret;
    }
}
