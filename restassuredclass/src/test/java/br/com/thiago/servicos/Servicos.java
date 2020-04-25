package br.com.thiago.servicos;

public enum Servicos {
	Users_ID ("/api/users/{id}"),
	getUsers_PAGE ("/api/users?page={id}");
	
	private final String valor;
	
	Servicos(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
