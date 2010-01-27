package me.fabiopereira.eurotrialsmonitor.model;

import java.text.DecimalFormat;

public class KPICalculator {

	public static Double getKpi(PerguntasRespondidas perguntasRespondidas) {
		if (perguntasRespondidas.isTodasRespondidas()) {
			double quantidadeRespostasNao = perguntasRespondidas.getQuantidadeRespostas(Resposta.NAO);
			double quantidadeRespostasSim = perguntasRespondidas.getQuantidadeRespostas(Resposta.SIM);
			return 100d * (quantidadeRespostasSim / (quantidadeRespostasSim + quantidadeRespostasNao));

		}
		return null;
	}

	public static String getKpiAsString(PerguntasRespondidas perguntasRespondidas) {
		Double kpi = getKpi(perguntasRespondidas);
		if (kpi != null) {
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			return twoDForm.format(kpi) + "%";
		} 
		return "";
	}

}
