package me.fabiopereira.eurotrialsmonitor.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.model.EtapaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.FormularioRespondido;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.PerguntaRespondida;
import me.fabiopereira.eurotrialsmonitor.model.Resposta;
import me.fabiopereira.eurotrialsmonitor.repository.FormularioRespondidoRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;

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
		try {
			FormularioRespondido formularioRespondido = getFormularioRespondido(request);
			request.setAttribute("formularioRespondido", formularioRespondido);
			populateFormularioHeaderFromRequest(formularioRespondido, request);
			populateRespostasFromRequest(formularioRespondido, request);
			formularioRespondidoRepository.add(formularioRespondido);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Dados inválidos - " + e.getMessage());
		}
	}

	private void populateFormularioHeaderFromRequest(FormularioRespondido formularioRespondido, HttpServletRequest request) throws ParseException {
		formularioRespondido.setEstudo(request.getParameter("estudo"));
		formularioRespondido.setCentro(request.getParameter("centro"));
		formularioRespondido.setNumeroVisita(Integer.valueOf(request.getParameter("numeroVisita")));
		formularioRespondido.setDataVisitaAsString(request.getParameter("dataVisita"));
	}

	private void populateRespostasFromRequest(FormularioRespondido formularioRespondido, HttpServletRequest request) {
		int etapaRespondidaIndex = 1;
		for (EtapaRespondida etapaRespondida : formularioRespondido.getEtapaRespondidas()) {
			int perguntaRespondidaIndex = 1;
			for (PerguntaRespondida perguntaRespondida : etapaRespondida.getPerguntasRespondidas()) {
				String respostaAsString = request.getParameter(String.format("etapaRespondidas[%s].perguntasRespondidas[%s].resposta",
						etapaRespondidaIndex, perguntaRespondidaIndex));
				String justificativa = request.getParameter(String.format("etapaRespondidas[%s].perguntasRespondidas[%s].justificativa",
						etapaRespondidaIndex, perguntaRespondidaIndex));
				perguntaRespondida.setJustificativa(justificativa);
				if (!StringUtils.isBlank(respostaAsString)) {
					Resposta resposta = Resposta.valueOf(respostaAsString);
					perguntaRespondida.setResposta(resposta);
				} else {
					perguntaRespondida.setResposta(null);
				}
				
				perguntaRespondidaIndex++;
			}
			etapaRespondidaIndex++;
		}
	}

	private FormularioRespondido getFormularioRespondido(HttpServletRequest request) {
		FormularioRespondido formularioRespondido = null;		
		String keyValue = request.getParameter("keyValue");
		if (!StringUtils.isBlank(keyValue)) {
			Key key = com.google.appengine.api.datastore.KeyFactory.stringToKey(keyValue);	
			formularioRespondido = formularioRespondidoRepository.findByPrimaryKey(key);
		} else {
			Monitor monitor = (Monitor) request.getAttribute("monitor");
			formularioRespondido = formularioBuilder.build(monitor);
		}
		return formularioRespondido;
	}
}
