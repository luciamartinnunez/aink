package proyecto.vista;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.velocity.app.VelocityEngine;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class VelocityRenderer implements ViewRenderer {

	private VelocityTemplateEngine engine;
	private File viewsDirectory;
	private static final String VIEWS_PATH = "./src/main/resources";
	private ObjectMapper mapper;

	public VelocityRenderer() {
		Properties properties = new Properties();
		properties.setProperty("file.resource.loader.path", VIEWS_PATH);
		engine = new VelocityTemplateEngine(new VelocityEngine(properties));
		mapper = new ObjectMapper();
		viewsDirectory = new File(VIEWS_PATH);
	}

	@Override
	public String render(Map<String, Object> model, String template)  {
		return engine.render(new ModelAndView(model, template));
	}


	@Override
	public Map<String, Object> toModel(Object object) {
		@SuppressWarnings("unchecked")
		Map<String, Object> model = mapper.convertValue(object, Map.class);
		return model;
	}

	@Override
	public String render(Object object, String template) {
		return render(toModel(object), template);
	}

	@Override
	public Map<String, Object> toModel(List<?> object, String variable) {
		Map<String,Object> model = new HashMap<>();
		List<Object> values = object.parallelStream().map(elem -> toModel(elem)).collect(Collectors.toList());
		model.put(variable, values);
		return model;
	}
}
