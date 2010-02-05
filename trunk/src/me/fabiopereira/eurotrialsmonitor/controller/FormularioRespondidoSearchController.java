package me.fabiopereira.eurotrialsmonitor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.exception.CampoInvalidoException;
import me.fabiopereira.eurotrialsmonitor.model.FormulariosRespondidos;
import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.repository.FormularioRespondidoRepository;

import org.apache.commons.lang.StringUtils;
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
	}

	@RequestMapping(method = RequestMethod.POST)
	public void search(HttpServletRequest request) {		
		Date startDate = getParameterDate(request, "startDate");
		Date endDate = getParameterDate(request, "endDate");
		String monitorUsuarioParam = getMonitorUsuarioParam(request);
		FormulariosRespondidos formulariosRespondidos = formularioRespondidoRepository.findByMonitorAndEntreDatasVisita(
				monitorUsuarioParam, startDate, endDate);
		request.setAttribute("formulariosRespondidos", formulariosRespondidos);

	}

	private String getMonitorUsuarioParam(HttpServletRequest request) {
		Monitor monitor = (Monitor) request.getSession().getAttribute("monitor");
		if (monitor.isAdmin()) {
			return request.getParameter("monitorUsuarioParam");			
		} 
		return monitor.getUsuario();
	}

	private Date getParameterDate(HttpServletRequest request, String parameter) {
		String date = request.getParameter(parameter);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (!StringUtils.isBlank(date)) {
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				throw new CampoInvalidoException(parameter, date);
			}
		}
		return null;
	}

}
