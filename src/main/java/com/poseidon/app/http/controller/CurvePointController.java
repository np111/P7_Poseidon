package com.poseidon.app.http.controller;

import com.poseidon.app.model.CurvePoint;
import com.poseidon.app.service.CurvePointService;
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
public class CurvePointController {
    private final CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.list());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePoint(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        curvePointService.create(curvePoint);
        model.addAttribute("curvePoints", curvePointService.list());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("curvePoint", curvePointService.read(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Long id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }

        curvePointService.update(id, curvePoint);
        model.addAttribute("curvePoints", curvePointService.list());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Long id, Model model) {
        curvePointService.delete(id);
        model.addAttribute("curvePoints", curvePointService.list());
        return "redirect:/curvePoint/list";
    }
}
