package com.poseidon.app.controller;

import com.poseidon.app.model.Rating;
import com.poseidon.app.service.RatingService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
public class RatingController {
    private final RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.list());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRating(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }

        ratingService.create(rating);
        model.addAttribute("ratings", ratingService.list());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rating", ratingService.read(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Long id, @Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }

        ratingService.update(id, rating);
        model.addAttribute("ratings", ratingService.list());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Long id, Model model) {
        ratingService.delete(id);
        model.addAttribute("ratings", ratingService.list());
        return "redirect:/rating/list";
    }
}
