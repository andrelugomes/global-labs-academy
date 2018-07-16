package com.spring.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.spring.web.model.Jedi;
import com.spring.web.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;
    
    @GetMapping("/jedi")
    public ModelAndView jedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView jediSearch(@RequestParam(value = "name") final String name) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findByNameContainingIgnoreCase(name));

        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public String createJedi(Model model) {

        model.addAttribute("jedi", new Jedi());
        
        return "new-jedi";
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            if (jedi.getId() == null) {
                return "new-jedi";
            } else {
                return "edit-jedi";
            }
        }
        
        repository.save(jedi);
        redirect.addFlashAttribute("message", "Jedi successfully saved.");

        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirect) {

        final Optional<Jedi> jedi = repository.findById(id);
        repository.delete(jedi.get());
        
        redirect.addFlashAttribute("message", "Jedi successfully deleted.");

        return "redirect:/jedi";
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {

        final Optional<Jedi> jedi = repository.findById(id);

        model.addAttribute("jedi", jedi.get());

        return "edit-jedi";
    }

}
