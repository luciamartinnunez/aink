package proyecto.controladores;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.MultipartConfigElement;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import doctor.DoctorFIS;
import doctor.DoctorFisImpl;
import doctor.model.report.Level;
import doctor.model.report.ReportEntry;
import proyecto.modelo.ConfiguracionDificultad;
import proyecto.persistencia.Repository;
import proyecto.vista.VelocityRenderer;
import proyecto.vista.ViewRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControladorAlumno {

	private static  Gson gson = new GsonBuilder().create();
	private final static ViewRenderer renderer = new VelocityRenderer();
	private static Repository<ConfiguracionDificultad> repositorio = new Repository<>(ConfiguracionDificultad.class);


	public static Route guardar =(request, response) -> {
		String arrayJson = "[]";
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
	    DoctorFIS doc = DoctorFisImpl.create();
	    //List<ReportEntry> solution =  doc.computeDCRestrictions(archivoStringBuilder.toString());
	    List<ReportEntry> solution = doc.computeDCURestrictions(archivoStringBuilder.toString());
	    
	    //ConfiguracionDificultad conf = repositorio.retrieve().get(0);
	    //Level level = ControladorProfesor.chooseLevel(conf.getDificultad());
	    
	    Set<String> feedback = solution.parallelStream().map(entry ->mapToJson(entry.getId(), entry.getMessages().get(Level.DETAILED))).map(jsonElem -> jsonElem.get("message").getAsString()).collect(Collectors.toSet());
	    
	     arrayJson = gson.toJson(feedback);
	     
	     
	     response.status(200);

	    return arrayJson;
		}catch(Exception e) {
			e.printStackTrace();
			response.status(400);
		}
		return "";

	};

    public static Route resolver=(Request req, Response res)->{
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostrar_resuelto_alum.vtl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
    
    public static Route gestionar=(Request req, Response res)->{
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "mostrar_información_PIE.vtl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
    
    private static JsonObject mapToJson(String id, String message) {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("message", message);
		return json;
	}
}