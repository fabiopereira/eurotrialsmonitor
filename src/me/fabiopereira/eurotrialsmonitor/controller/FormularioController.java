package me.fabiopereira.eurotrialsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/" + FormularioController.URL)
public class FormularioController {
	public static final String URL = "formulario";

	private final FormularioBuilder formularioBuilder;

	@Autowired
	public FormularioController(FormularioBuilder formularioBuilder) {
		this.formularioBuilder = formularioBuilder;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(ModelMap modelMap) {
		modelMap.addAttribute("formulario", formularioBuilder.build());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void post() {
	}
}
