package rinha.api.rest.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import rinha.api.rest.models.ExampleResponseModel;
import rinha.domain.entities.Example;
import rinha.api.rest.endpoints.ExampleEndpoint;

@Component
public class ExampleAssembler extends RepresentationModelAssemblerSupport<Example, ExampleResponseModel> {

	public ExampleAssembler() {
		super(ExampleEndpoint.class, ExampleResponseModel.class);
	}

	@Override
	public ExampleResponseModel toModel(Example processVersion) {
		final ExampleResponseModel model = new ExampleResponseModel(processVersion);
		model.add(linkTo(methodOn(ExampleEndpoint.class).get(processVersion.getId())).withSelfRel());
		return model;
	}
}