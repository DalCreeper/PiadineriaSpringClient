package com.advancia.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.advancia.spring.service.PiadinaService;
import com.advancia.spring.soapclient.Piadina;

@Controller
public class PiadinaController {

	@Autowired
    private PiadinaService piadinaService;
	
	@GetMapping("/home")
    public String home() {
        return "home";
    }
	
    @PostMapping("/piadinas")
    public String getPiadinas(Model model, HttpSession session) {
        List<Piadina> piadinas = piadinaService.getPiadinas();
        if(piadinas != null && !piadinas.isEmpty()) {
        	model.addAttribute("piadinas", piadinas);
        	session.setAttribute("piadinas", piadinas);
        } else {
            System.out.println("No piadinas found.");
        }
        return "piadinas";
    }
}

// http://localhost:8080/PiadineriaAdvanciaClient/home