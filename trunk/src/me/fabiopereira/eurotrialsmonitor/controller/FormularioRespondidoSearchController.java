package me.fabiopereira.eurotrialsmonitor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.FormulariosRespondidos;
import me.fabiopereira.eurotrialsmonitor.repository.FormularioRespondidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/" + FormularioRespondidoSearchController.URL)
public class FormularioRespondidoSearchController {
	public static final String URL = "formularioRespondidoSearch";

	private final FormularioRespondidoRepository formularioRespondidoRepository;

	@Autowired
	public FormularioRespondidoSearchController(FormularioRespondidoRepository formularioRespondidoRepository) {
		this.formularioRespondidoRepository = formularioRespondidoRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(HttpServletRequest request) {
		List<FormularioRespondido> formulariosRespondidos = formularioRespondidoRepository.findAll();		
		request.setAttribute("formulariosRespondidos", new FormulariosRespondidos(formulariosRespondidos));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void search(HttpServletRequest request) {
		
	}

}
