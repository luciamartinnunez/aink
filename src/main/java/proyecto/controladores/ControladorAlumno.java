package proyecto.controladores;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import spark.Request;
import spark.Response;
import spark.Route;

public class ControladorAlumno {

	//private final static ViewRenderer renderer = new FreemarkerRenderer();

	public static Route guardar =(request, response) -> {
		try {
			request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("./temp"));
			InputStream archivoInputStream = request.raw().getPart("archivo").getInputStream();
			BufferedReader archivoBufferedReader = new BufferedReader(new InputStreamReader(archivoInputStream));
		    StringBuilder archivoStringBuilder = new StringBuilder();
		    String lineaArchivo;
		    while ((lineaArchivo = archivoBufferedReader.readLine()) != null) {
		        archivoStringBuilder.append(lineaArchivo);
		        archivoStringBuilder.append(System.lineSeparator());
		    }
	    System.out.println(archivoStringBuilder.toString());
	    response.status(200);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.status(400);
		}
	    String mensaje = "Archivo guardado con éxito";

        return mensaje;
     };


	public static Route resolver =(Request req, Response res)->{

        Map<String, Object> attr= new HashMap<>();
        try {
			return "";//renderer.render(attr, "mostrar_resuelto_alum.ftl");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
        return " ";
    };
}