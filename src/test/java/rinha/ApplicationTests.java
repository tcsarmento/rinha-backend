package rinha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@Sql("/dataset.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class ApplicationTests<T extends ApplicationTests<?>> {

	@SuppressWarnings("unchecked")
	private final T endpointTest = (T) this;
	
	private final YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();
	
	@Autowired
	protected MockMvc mockMvc;

	protected ApplicationTests() {
		super();
		yamlProperties.setResources(
				new ClassPathResource("scenarios/endPoints/" + endpointTest.getClass().getSimpleName() + ".yml"));
	}

	protected String getScenarioRequestBody(final String scenario) {
		return yamlProperties.getObject().getProperty(scenario + ".requestBody");
	}
	
}