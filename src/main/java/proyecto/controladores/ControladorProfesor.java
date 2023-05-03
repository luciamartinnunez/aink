package proyecto.controladores;
import java.util.HashMap;
import java.util.Map;

import proyecto.modelo.ConfiguracionDificultad;
import proyecto.persistencia.Repository;
import proyecto.vista.FreemarkerRenderer;
import proyecto.vista.ViewRenderer;
import spark.Request;
import spark.Response;
import spark.Route;
public class ControladorProfesor {

	private final static ViewRenderer renderer = new FreemarkerRenderer();
	private static Repository<ConfiguracionDificultad> repositorio = new Repository<>(ConfiguracionDificultad.class);
	
	public static Route resolver=(Request req, Response res)->{
        String archivo= req.queryParams("archivo");
        String body= req.queryParams("nivel_feedback");
        Integer nivel_feedback = Integer.valueOf(body);
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostrar_resuelto_prof.ftl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
    public static Route setupConfiguration=(Request request, Response response)->{

		String body = request.queryParams("level");
		Integer level=Integer.valueOf(body);
		ConfiguracionDificultad conf = new ConfiguracionDificultad(level);
		//repositorio.persist(conf);
		System.out.println(repositorio.retrieve());
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "nivel.ftl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
}
