package com.poseidon.app.http.controller;

import com.poseidon.app.model.Trade;
import com.poseidon.app.service.TradeService;
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
public class TradeController {
    private final TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.list());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }

        tradeService.create(trade);
        model.addAttribute("trades", tradeService.list());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("trade", tradeService.read(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Long id, @Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }

        tradeService.update(id, trade);
        model.addAttribute("trades", tradeService.list());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Long id, Model model) {
        tradeService.delete(id);
        model.addAttribute("trades", tradeService.list());
        return "redirect:/trade/list";
    }
}
