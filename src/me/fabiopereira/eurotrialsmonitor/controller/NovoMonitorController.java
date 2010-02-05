package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.exception.CampoObrigatorioException;
import me.fabiopereira.eurotrialsmonitor.exception.EurotrialsException;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/novoMonitor")
public class NovoMonitorController {

	private final MonitorRepository monitorRepository;

	@Autowired
	public NovoMonitorController(MonitorRepository monitorRepository) {
		super();
		this.monitorRepository = monitorRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get() {
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(HttpServletRequest request) {		
		String usuario = getCampoObrigatorio(request, "usuario");
		
		if (monitorRepository.findByUsuario(usuario) != null) {
			throw new EurotrialsException("Usuário já existente, favor escolher outro.");
		}

		Monitor monitor = new Monitor();
		monitor.setUsuario(usuario);
		monitorRepository.persist(monitor);

		request.setAttribute("infoMessage", "Usuário " + usuario + " criado com sucesso.");
	}

	private String getCampoObrigatorio(HttpServletRequest request, String parameter) {
		String parameterValue = request.getParameter(parameter);
		if (StringUtils.isBlank(parameterValue)) {
			throw new CampoObrigatorioException();
		}
		return parameterValue;
	}

}
