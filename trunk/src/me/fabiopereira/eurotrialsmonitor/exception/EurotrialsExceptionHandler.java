package me.fabiopereira.eurotrialsmonitor.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class EurotrialsExceptionHandler extends SimpleMappingExceptionResolver {
	
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	request.setAttribute("errorMessage", getMessage(ex));
        return mesmaPagina(request);        
    }

	private String getMessage(Exception ex) {		
        if (EurotrialsException.class.isAssignableFrom(ex.getClass()) && !StringUtils.isBlank(ex.getMessage())) {
        	return ex.getMessage();
        }		
    	ex.printStackTrace();
		return "Ocorreu um erro inesperado, por favor entre em contato com o administrador do sistema. Mensagem: " + ex.getMessage();
	}

	private ModelAndView mesmaPagina(HttpServletRequest request) {
		return new ModelAndView(request.getRequestURI());
	}
}
