package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.MonitorLogin;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/monitorLogout")
public class MonitorLogoutController {

    @RequestMapping(method = RequestMethod.GET)
	public String monitorLoginGet(@ModelAttribute MonitorLogin monitorLogin, BindingResult bindingResult, 
			HttpServletRequest request) {
    	request.getSession().removeAttribute("monitor");
    	return "redirect:monitorLogin";
	}
}
