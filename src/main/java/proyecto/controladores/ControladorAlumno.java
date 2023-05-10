package proyecto.controladores;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	public static Route guardar =(request, response) -> {
        InputStream archivoInputStream = request.raw().getPart("archivo").getInputStream();
	    BufferedReader archivoBufferedReader = new BufferedReader(new InputStreamReader(archivoInputStream));
	    StringBuilder archivoStringBuilder = new StringBuilder();
	    String lineaArchivo;
	    while ((lineaArchivo = archivoBufferedReader.readLine()) != null) {
	        archivoStringBuilder.append(lineaArchivo);
	        archivoStringBuilder.append(System.lineSeparator());
	    }
	    Repository<StringBuilder> repositorio = new Repository<>(null);
	    repositorio.persist(archivoStringBuilder);
        
        String mensaje = "Archivo guardado con éxito ";
	    return mensaje;};
    
	public static Route resolver =(Request req, Response res)->{
		
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