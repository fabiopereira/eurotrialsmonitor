package me.fabiopereira.eurotrialsmonitor.controller;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/" + FormularioController.URL)
public class FormularioController {
	public static final String URL = "formulario";

    @RequestMapping(method = RequestMethod.GET)
	public void get() {
	}

    @RequestMapping(method = RequestMethod.POST)
	public void post() {
	}	
}
