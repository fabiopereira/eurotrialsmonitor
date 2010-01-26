package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.Resposta;
import me.fabiopereira.eurotrialsmonitor.repository.FormularioRespondidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// SessionAttributes(types = Monitor.class)
@RequestMapping("/" + FormularioRespondidoController.URL)
public class FormularioRespondidoController {
	public static final String URL = "formularioRespondido";

	private final FormularioBuilder formularioBuilder;
	private final FormularioRespondidoRepository formularioRespondidoRepository;

	@Autowired
	public FormularioRespondidoController(FormularioBuilder formularioBuilder, FormularioRespondidoRepository formularioRespondidoRepository) {
		this.formularioBuilder = formularioBuilder;
		this.formularioRespondidoRepository = formularioRespondidoRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(ModelMap modelMap, HttpServletRequest request) {
		Monitor monitor = (Monitor) request.getAttribute("monitor");
		FormularioRespondido formularioRespondido = formularioBuilder.build(monitor);
		request.setAttribute("formularioRespondido", formularioRespondido);
		// modelMap.addAttribute("formularioRespondido", formularioRespondido);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(HttpServletRequest request) {
		Monitor monitor = (Monitor) request.getAttribute("monitor");
		FormularioRespondido formularioRespondido = formularioBuilder.build(monitor);

		int etapaRespondidaIndex = 1;
		for (EtapaRespondida etapaRespondida : formularioRespondido.getEtapaRespondidas()) {
			int perguntaRespondidaIndex = 1;
			for (PerguntaRespondida perguntaRespondida : etapaRespondida.getPerguntasRespondidas()) {
				String respostaAsString = request.getParameter(String.format("etapaRespondidas[%s].perguntasRespondidas[%s].resposta",
						etapaRespondidaIndex, perguntaRespondidaIndex));
				if (respostaAsString != null) {
					Resposta resposta = Resposta.valueOf(respostaAsString);
					perguntaRespondida.setResposta(resposta);
				} else {
					perguntaRespondida.setResposta(null);
				}
				perguntaRespondidaIndex++;
			}
			etapaRespondidaIndex++;
		}

		formularioRespondidoRepository.add(formularioRespondido);
		request.setAttribute("formularioRespondido", formularioRespondido);
	}
	// @ModelAttribute
	// FormularioRespondido getFormularioRespondido(@ModelAttribute Monitor
	// monitor) {
	// return formularioBuilder.build(monitor);
	// }
}
