package rinha.api.rest.endpoints;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;

import rinha.ApplicationTests;

public class PessoaEndpointTest extends ApplicationTests<PessoaEndpointTest> {

	private String scenariosPath = "scenarios/endPoints/pessoaEndpointTest.yml";

	@Test
	public void shouldReturnOkGetProcessById() throws Exception {
		final String uri = fromPath("/pessoas/550e8400-e29b-41d4-a716-446655440000").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("550e8400-e29b-41d4-a716-446655440000"))
				.andExpect(jsonPath("$.apelido").value("CACHORRO"))
				.andExpect(jsonPath("$.nome").value("NINA"))
				.andExpect(jsonPath("$.nascimento").value("1985-12-01"))
				.andExpect(jsonPath("$.stack[0]").value("PHP"))
				.andExpect(jsonPath("$.stack[1]").value("JAVA"))
				.andExpect(jsonPath("$.stack[2]").value("KOTLIN"));
	}


	@Test
	@Disabled
	public void shouldReturnOkGetTermoNINA() throws Exception {
		final String uri = fromPath("/pessoas").queryParam("t","NINA").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value("550e8400-e29b-41d4-a716-446655440000"))
				.andExpect(jsonPath("$[0].apelido").value("CACHORRO"))
				.andExpect(jsonPath("$[0].nome").value("NINA"))
				.andExpect(jsonPath("$[0].nascimento").value("1985-12-01"))
				.andExpect(jsonPath("$[0].stack[0]").value("PHP"))
				.andExpect(jsonPath("$[0].stack[1]").value("JAVA"))
				.andExpect(jsonPath("$[0].stack[2]").value("KOTLIN"));
	}

	@Test
	@Disabled
	public void shouldReturnOkGetTermoCHORR() throws Exception {
		final String uri = fromPath("/pessoas").queryParam("t","CHORR").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value("550e8400-e29b-41d4-a716-446655440000"))
				.andExpect(jsonPath("$[0].apelido").value("CACHORRO"))
				.andExpect(jsonPath("$[0].nome").value("NINA"))
				.andExpect(jsonPath("$[0].nascimento").value("1985-12-01"))
				.andExpect(jsonPath("$[0].stack[0]").value("PHP"))
				.andExpect(jsonPath("$[0].stack[1]").value("JAVA"))
				.andExpect(jsonPath("$[0].stack[2]").value("KOTLIN"));
	}

	@Test
	public void shouldReturnBadRequestIFParamTNotInformed() throws Exception {
		final String uri = fromPath("/pessoas").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReturnOkGetContagem() throws Exception {
		final String uri = fromPath("/contagem-pessoas").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(1));
	}

	@Test
	@Disabled
	public void shouldReturnOkGetTermoPAYSANDUWithEmptyList() throws Exception {
		final String uri = fromPath("/pessoas").queryParam("t","PAYSANDU").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));
	}

	@Test
	public void shouldReturnCreatedPost() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturnCreatedPost");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void shouldReturn422IfApelidoExists() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfApelidoExists");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfApelidoIsnull() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfApelidoIsNull");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfNomeIsnull() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfNomeIsNull");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfNomeInvalid() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfNomeInvalid");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfStackInvalid() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfStackInvalid");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReturnCreatedIfStackIsNull() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturnCreatedIfStackIsNull");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void shouldReturn422IfStackElementMoreThan32Characters() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfStackElementMoreThan32Characters");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfApelidoMoreThan32Characters() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfApelidoMoreThan32Characters");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfApelidoDifferentString() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfApelidoDifferentString");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfNomeMoreThan100Characters() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfNomeMoreThan100Characters");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldReturn422IfDateInvalidFormat() throws Exception {
		final String uri = "/pessoas";

		final YamlLoader content = new YamlLoader(scenariosPath, "shouldReturn422IfDateInvalidFormat");

		mockMvc.perform(post(uri)
						.contentType(MediaTypes.HAL_JSON)
						.content(content.getInput()))
				//.andExpect(header().string(LOCATION, containsString(uri)))
				.andDo(print()).andExpect(status().isBadRequest());
	}
}