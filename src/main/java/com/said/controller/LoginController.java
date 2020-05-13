package com.said.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
/*    @Autowired
    PasswordEncoder passwordEncoder;*/
    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, HttpServletRequest request, Model model) {
        //System.out.println("НАЧАЛО:"+ passwordEncoder.encode("admin") + ":КОНЕЦ");
        if(authentication != null) {
            return "redirect:/user";
        }
        if(request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }
}
