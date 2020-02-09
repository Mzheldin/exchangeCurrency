package com.example.demo.controllers;

import com.example.demo.entities.History;
import com.example.demo.entities.Valute;
import com.example.demo.services.HistoryService;
import com.example.demo.services.RateService;
import com.example.demo.services.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private HistoryService historyService;
    private ValuteService valuteService;
    private RateService rateService;

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Autowired
    public void setValuteService(ValuteService valuteService) {
        this.valuteService = valuteService;
    }

    @Autowired
    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "lastHistoryId", required = false) String lastHistoryId){
        model.addAttribute("valutes", valuteService.findAll());
        model.addAttribute("history", historyService.checkLastHistory(lastHistoryId));
        return "convert_page";
    }

    @PostMapping("/convert")
    public String saveHistory(@ModelAttribute(name = "history") History history, HttpServletResponse response) {
        if (history.getSourceSumm() != null)
            response.addCookie(new Cookie("lastHistoryId",
                    historyService.save(historyService.exchangeUpdateHistory(history)).getId().toString()));
        return "redirect:/";
    }

    @GetMapping("/history")
    public String showHistory(Model model,
                              @RequestParam(name = "date", required = false) String date,
                              @RequestParam(name = "sourceValute", required = false) String sourceValute,
                              @RequestParam(name = "targetValute", required = false) String targetValute) {
        List<History> histories = new ArrayList<>();
        if (date != null && sourceValute != null && targetValute != null)
            histories = historyService.findAllByDateAndSourceAndTargetValutes(Date.valueOf(date), sourceValute, targetValute);
        model.addAttribute("valutes", valuteService.findAll());
        model.addAttribute("histories", histories);
        model.addAttribute("valuteService", valuteService);
        return "history_page";
    }
}
