package rinha.api.rest.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import rinha.domain.entities.Example;

@JsonPropertyOrder({ "id", "processKey", "description", "responsibleQueue", "businessProcessName",
		"businessProcessDescription", "online", "active" })
@Relation(value = "process", collectionRelation = "processes")
public class ExampleResponseModel extends RepresentationModel<ExampleResponseModel> {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("processKey")
	private String processKey;

	@JsonProperty("description")
	private String description;

	@JsonProperty("responsibleQueue")
	private String responsibleQueue;

	@JsonProperty("businessProcessName")
	private String businessProcessName;

	@JsonProperty("businessProcessDescription")
	private String businessProcessDescription;

	@JsonProperty("online")
	private Boolean online;

	@JsonProperty("active")
	private Boolean active;

	public ExampleResponseModel(final Example processVersion) {
		super();
		this.id = processVersion.getId();
		this.processKey = processVersion.getProcessKey();
		this.description = processVersion.getDescription();
		this.responsibleQueue = processVersion.getResponsibleQueue();
		this.businessProcessName = processVersion.getBusinessProcessName();
		this.businessProcessDescription = processVersion.getBusinessProcessDescription();
		this.online = processVersion.isOnline();
		this.active = processVersion.isActive();
	}
}