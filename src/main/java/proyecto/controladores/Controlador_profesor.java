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
public class Controlador_profesor {

	private final static ViewRenderer renderer = new FreemarkerRenderer();
	private static Repository<ConfiguracionDificultad> repositorio = new Repository<>(ConfiguracionDificultad.class);
	
	public String resolver(Request req, Response res){
        String archivo, nivel_feedback, sol=" ";
        archivo= req.queryParams("archivo");
        nivel_feedback= req.queryParams("nivel_feedback");
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostar_resuelto.ftl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sol= "Error";
		}
        return sol;

    }
    public static Route setupConfiguration=(Request request, Response response)->{

		String body = request.queryParams("level");
		Integer level=Integer.valueOf(body);
		ConfiguracionDificultad conf = new ConfiguracionDificultad(0);
		//repositorio.persist(conf);
		System.out.println(repositorio.retrieve());
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostar_resuelto.ftl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
}
