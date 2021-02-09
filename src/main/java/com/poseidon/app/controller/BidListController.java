package com.poseidon.app.controller;

import com.poseidon.app.model.BidList;
import com.poseidon.app.service.BidListService;
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
public class BidListController {
    private final BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.list());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidList(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.create(bidList);
        model.addAttribute("bidLists", bidListService.list());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bidList", bidListService.read(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBidList(@PathVariable("id") Long id, @Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.update(id, bidList);
        model.addAttribute("bidLists", bidListService.list());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBidList(@PathVariable("id") Long id, Model model) {
        bidListService.delete(id);
        model.addAttribute("bidLists", bidListService.list());
        return "redirect:/bidList/list";
    }
}
