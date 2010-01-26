package me.fabiopereira.eurotrialsmonitor.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class PerguntaRespondida extends PersistedModel {

	private static final long serialVersionUID = 20100103L;

	@Persistent
	private Resposta resposta;
	@Persistent
	private String justifcativa;
	@Persistent
	private Integer perguntaNumero;

	public PerguntaRespondida() {
	}

	public PerguntaRespondida(EtapaRespondida etapaRespondida, Pergunta pergunta) {
		super();
		this.perguntaNumero = pergunta.getNumero();
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public String getJustifcativa() {
		return justifcativa;
	}

	public void setJustifcativa(String justifcativa) {
		this.justifcativa = justifcativa;
	}

	public Integer getPerguntaNumero() {
		return perguntaNumero;
	}

	public void setPerguntaNumero(Integer perguntaNUmero) {
		this.perguntaNumero = perguntaNUmero;
	}

}
