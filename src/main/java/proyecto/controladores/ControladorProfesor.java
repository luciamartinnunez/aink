package proyecto.controladores;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.MultipartConfigElement;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
public class ControladorProfesor {

	private static  Gson gson = new GsonBuilder().create();
	private final static ViewRenderer renderer = new VelocityRenderer();
	private static Repository<ConfiguracionDificultad> repositorio = new Repository<>(ConfiguracionDificultad.class);

	public static Route guardarNivel = (request, response) -> {
	    int entero = -1;
	    String mensaje = "";

	    String enteroStr = request.queryParams("entero");
	    
	    if (enteroStr != null && !enteroStr.isEmpty()) {
	        try {
	            entero = Integer.parseInt(enteroStr);
	            ConfiguracionDificultad i = new ConfiguracionDificultad(entero);
	            repositorio.update(i);
	            response.status(200);
	            mensaje = "Guardado con éxito el entero: " + entero;
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.status(400);
	            mensaje = "El valor del entero no es válido";
	        }
	    } else {
	        response.status(400);
	        mensaje = "No se ha proporcionado un valor para el entero";
	    }

	    return mensaje;
	};
	public static Route login =(request, response) -> {
    	Map<String, Object> attr= new HashMap<>();
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        if (username.equals("profesor") && password.equals("profesor")) {
            request.session().attribute("isLoggedIn", true);
            response.redirect("/prof");
        } else {
            HashMap<String, Object> model = new HashMap<>();
            model.put("isLoggedIn", false);
            model.put("errorMessage", "Usuario o contraseña incorrectos.");
			return renderer.render(model, "login.vtl");
        }
        return null;
        
	};
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
	    List<ReportEntry> solution =  doc.computeDCRestrictions(archivoStringBuilder.toString());
	    List<ReportEntry> solution2 = doc.computeDCURestrictions(archivoStringBuilder.toString());
	    solution.addAll(solution2);
	    Level level = chooseLevel( Integer.parseInt(request.queryParams("entero")) );

	    List<JsonObject> feedback = solution.parallelStream().map(entry ->mapToJson(entry.getId(), entry.getMessages().get(level)))
	    .collect(Collectors.toList());
	    
	     arrayJson = gson.toJson(feedback);
	     
	     
	     response.status(200);
	    String mensaje = "Archivo guardado con éxito con el entero: " + request.queryParams("entero");
//	
//	    response.header("X-Mensaje", mensaje);
//	    System.out.println("hola");
	    return arrayJson;
		}catch(Exception e) {
			e.printStackTrace();
			response.status(400);
		}
		return "";

	};


	private static JsonObject mapToJson(String id, String message) {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("message", message);
		return json;
	}

	public static Level chooseLevel(int levelChoosen) {
		Level level = null;
		switch(levelChoosen) {
			case 0:
				level = Level.MIN;
				break;
			case 1:
				level = Level.HINT;
				break;
			case 2:
				level = Level.DETAILED;
				break;
			case 3:
				level = Level.SOLUTION;
				break;
		}
		return level;
	}

	public static Route resolver=(Request req, Response res)->{
        Map<String, Object> attr= new HashMap<>();
        Boolean isLoggedIn = false;
        try {
        	isLoggedIn = req.session().attribute("isLoggedIn");
            if (isLoggedIn == null || !isLoggedIn) {
            	res.redirect("/login");
                return null;
            }
			return renderer.render(attr, "mostrar_resuelto_prof.vtl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
    public static Route setupConfiguration=(Request req, Response res)->{
        Map<String, Object> attr= new HashMap<>();
        Boolean isLoggedIn = false;
        try {
        	isLoggedIn = req.session().attribute("isLoggedIn");
            if (isLoggedIn == null || !isLoggedIn) {
            	res.redirect("/login");
                return null;
            }
			return renderer.render(attr, "nivel.vtl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
	public static Route logout =(request, response) -> {
    	Map<String, Object> attr= new HashMap<>();
        request.session().attribute("isLoggedIn", false);
        response.redirect("/alum");
        return null;
        
	};
    public static Route setupLogin=(Request req, Response res)->{
        Map<String, Object> attr= new HashMap<>();
        try {
			return renderer.render(attr, "login.vtl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return " ";

    };
//    public static List<ReportEntry> resolver(String uml){
//
//    	 DoctorFisAux doc = new DoctorFisAux(uml.toString());
//    	 List<ReportEntry> entries =doc.computeDCRestrictions(null, null);
//    	 entries.addAll(doc.computeDCURestrictions(uml));
//    	 return entries;
//    }


}
