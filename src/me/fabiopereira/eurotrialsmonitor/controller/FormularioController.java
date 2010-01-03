package me.fabiopereira.eurotrialsmonitor.controller;

import java.util.List;

import me.fabiopereira.eurotrialsmonitor.model.Etapa;
import me.fabiopereira.eurotrialsmonitor.repository.EtapaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/" + FormularioController.URL)
public class FormularioController {
	public static final String URL = "formulario";
	
	private final EtapaRepository etapaRepository;
	
	@Autowired
    public FormularioController(EtapaRepository etapaRepository) {
		super();
		this.etapaRepository = etapaRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(ModelMap modelMap) {
		List<Etapa> etapas = etapaRepository.findAll();
		modelMap.addAttribute("etapas", etapas);
	}

    @RequestMapping(method = RequestMethod.POST)
	public void post() {
	}	
}
