package rinha.api.rest.models;

import javax.validation.constraints.NotNull;

public class ExampleRequestModel {

	@NotNull
	private Boolean active;

	public ExampleRequestModel() {
		super();
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}