package rinha.api.rest.endpoints;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rinha.api.rest.assemblers.ExampleAssembler;
import rinha.api.rest.models.ExampleRequestModel;
import rinha.api.rest.models.ExampleResponseModel;
import rinha.domain.entities.Example;
import rinha.domain.enumeration.ExceptionMessagesEnum;
import rinha.domain.exceptions.NotFoundException;
import rinha.domain.exceptions.PreconditionFailedException;
import rinha.domain.services.ExampleService;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ExampleEndpoint {

	public static final String PROCESS_RESOURCE_PATH = "/rs/processes";
	public static final String PROCESS_SELF_PATH = PROCESS_RESOURCE_PATH + "/{processId}";
	public final static String PROCESSES_BY_VARIABLES = PROCESS_RESOURCE_PATH + "{matrixVariables}";

	@Autowired
	private ExampleService exampleService;

	@Autowired
	private ExampleAssembler exampleAssembler;
	
	private static final String EXAMPLE_TAG = "tag-example-830041c4145f66f1a6d4b0ae11111111";

	@GetMapping(value = PROCESS_SELF_PATH)
	public ResponseEntity<ExampleResponseModel> get(@PathVariable("processId") final Long processId) {

		final Example example = exampleService.getById(processId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.EXAMPLE_NOT_FOUND));

		return ResponseEntity.ok().eTag(EXAMPLE_TAG).body(exampleAssembler.toModel(example));
	}

	@GetMapping(value = PROCESSES_BY_VARIABLES)
	public ResponseEntity<CollectionModel<ExampleResponseModel>> findByFilter(Pageable pageable,
			@MatrixVariable(value = "processKey") final String processKey) {
		return new ResponseEntity<>(OK);
	}
	
	@PatchMapping(value = PROCESS_SELF_PATH)
	public ResponseEntity<?> updateStatus(@RequestHeader("If-Match") final String ifMatch,
			@PathVariable("processId") final Long processId,
			@RequestBody @Valid final ExampleRequestModel requestModel) {

		final Example example = exampleService.getById(processId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.EXAMPLE_NOT_FOUND));
		
		if (!ifMatch.equalsIgnoreCase(EXAMPLE_TAG)) {
			throw new PreconditionFailedException(ExceptionMessagesEnum.PRECONDITION_FAILED_DEFAULT);
		}
		
		exampleService.updateStatus(example, requestModel.isActive());
		return ResponseEntity.noContent().eTag("tag-example-830041c4145f66f1a6d4b0ae22222222").build();
	}
	
}