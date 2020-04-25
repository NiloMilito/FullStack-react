package br.com.thiago.test.entidate;

public class PessoaResponse {

	private String name;
	private String job;
	private String id;
	private String createdAt;
	private String updatedAt;
	
	public PessoaResponse(String name, String job, String id, String createdAt, String updatedAt) {
		super();
		this.name = name;
		this.job = job;
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public PessoaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
