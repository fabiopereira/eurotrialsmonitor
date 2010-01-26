package me.fabiopereira.eurotrialsmonitor.controller;

import javax.servlet.http.HttpServletRequest;

import me.fabiopereira.eurotrialsmonitor.model.Monitor;
import me.fabiopereira.eurotrialsmonitor.model.MonitorLogin;
import me.fabiopereira.eurotrialsmonitor.repository.MonitorRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/monitorLogin")
public class MonitorLoginController {

	private final MonitorRepository monitorRepository;
	
	@Autowired
	public MonitorLoginController(MonitorRepository monitorRepository) {
		this.monitorRepository = monitorRepository;
	}
	
    @RequestMapping(method = RequestMethod.GET)
	public void monitorLoginGet(@ModelAttribute MonitorLogin monitorLogin, BindingResult bindingResult) {
	}

    @RequestMapping(method = RequestMethod.POST)
	public String monitorLogin(@ModelAttribute MonitorLogin monitorLogin, 
			BindingResult bindingResult, HttpServletRequest request) {
    	if (StringUtils.isBlank(monitorLogin.getId())) {
			bindingResult.reject("invalid.login");
			return null;
    	}
		Monitor monitor = monitorRepository.findByUsuario(monitorLogin.getId());
		if (monitor == null) {
			bindingResult.reject("invalid.login");
			return null;
		}
		request.getSession().setAttribute("monitor", monitor);
		return "redirect:" + FormularioRespondidoSearchController.URL;
	}
	
}
