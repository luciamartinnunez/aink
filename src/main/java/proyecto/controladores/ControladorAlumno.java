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

public class ControladorAlumno {
	
	private final static ViewRenderer renderer = new FreemarkerRenderer();	
	
	public static Route resolver =(Request req, Response res)->{
		//configurar dificultad
        String archivo= req.queryParams("archivo");
        //Integer nivel_feedback = Integer.valueOf(persistencia);
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostrar_resuelto_alum.ftl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";
    };
}