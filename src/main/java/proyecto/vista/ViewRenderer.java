package proyecto.vista;

import java.util.List;
import java.util.Map;

public interface ViewRenderer {

	String render(Object object, String template) throws Exception;
	String render(Map<String, Object> model, String template) throws Exception;
	Map<String, Object> toModel(Object object);
	Map<String, Object> toModel(List<?> object, String variable);
}