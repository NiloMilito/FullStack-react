package br.com.thiago.test.entidate;

public class PessoaRequest {

	private String name;
	private String job;
	
	public PessoaRequest(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
	public PessoaRequest() {
		super();
	}
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
