package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.User;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("isLoginorSignup", true);
        return "auth/login";
    }

    @GetMapping("/signup")
    public String create(Model model){
        model.addAttribute("isLoginorSignup", true);
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String store(@Valid @ModelAttribute("user") User user, 
                        @RequestParam("password") String password,
                        @RequestParam("confirmPassword") String confirmPassword,
                        @RequestParam("username") String username,
                        BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "/signup";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "The passwords don't match");
            return "/signup";
        }
        
        userService.createUser(username, confirmPassword);
        return "redirect:/pizzas";
    }
}
