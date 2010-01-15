package me.fabiopereira.eurotrialsmonitor.controller;

import me.fabiopereira.eurotrialsmonitor.bootstrap.EurotrialsBootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/" + BootstrapController.URL)
public class BootstrapController {
	public static final String URL = "bootstrap";

	private final EurotrialsBootstrap eurotrialsBootstrap;

	@Autowired
	public BootstrapController(EurotrialsBootstrap eurotrialsBootstrap) {
		this.eurotrialsBootstrap = eurotrialsBootstrap;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(ModelMap modelMap) {
		eurotrialsBootstrap.bootstrapAll();
	}

}
