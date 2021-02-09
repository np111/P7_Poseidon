package com.poseidon.app.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@Scope("singleton")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }
}
