package proyecto.controladores;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
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
	
	public static Route guardarNivel =(request, response) -> {
	    int entero = Integer.parseInt(request.queryParams("entero"));
	    return "Nivel recibido con éxito";
	};
	
	public static Route guardar =(request, response) -> {
		InputStream archivoInputStream = request.raw().getPart("archivo").getInputStream();
	    BufferedReader archivoBufferedReader = new BufferedReader(new InputStreamReader(archivoInputStream));
	    StringBuilder archivoStringBuilder = new StringBuilder();
	    String lineaArchivo;
	    while ((lineaArchivo = archivoBufferedReader.readLine()) != null) {
	        archivoStringBuilder.append(lineaArchivo);
	        archivoStringBuilder.append(System.lineSeparator());
	    }
	    int entero = Integer.parseInt(request.queryParams("entero"));
	    String mensaje = "Archivo guardado con éxito con el entero: " + entero;
        return mensaje;
	    
	};
	
	public static Route resolver=(Request req, Response res)->{
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
