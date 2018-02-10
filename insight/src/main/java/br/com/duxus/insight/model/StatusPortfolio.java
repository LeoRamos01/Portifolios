package br.com.duxus.insight.model;

public enum StatusPortfolio {
	CONCLUIDO("Concluído"), DESENVOLVIMENTO("Desenvolvimento");

	private String descricao;

	StatusPortfolio(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
