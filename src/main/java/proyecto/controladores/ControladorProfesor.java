package proyecto.controladores;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.MultipartConfigElement;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import doctor.DoctorFIS;
import doctor.DoctorFisImpl;
import doctor.Utils;
import doctor.model.report.Level;
import doctor.model.report.ReportEntry;
import doctor.model.restrictions.Restriction;
import doctor.model.restrictions.dc.*;
import doctor.model.restrictions.dcu.*;
import helio.blueprints.TranslationUnit;
import helio.blueprints.UnitBuilder;
import helio.builder.siot.rx.SIoTRxBuilder;
import proyecto.DoctorFisAux;
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

	public static Route guardarNivel = (request, response) -> {
	    int entero = -1;
	    String mensaje = "";

	    String enteroStr = request.queryParams("entero");

	    if (enteroStr != null && !enteroStr.isEmpty()) {
	        try {
	            entero = Integer.parseInt(enteroStr);
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


	public static Route guardar =(request, response) -> {
		int entero  = -1;
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
	    
	   
	    List<ReportEntry> solution = resolver(archivoStringBuilder.toString());
	    solution.stream().forEach(elem -> System.out.println(elem));
	    //System.out.println(archivoStringBuilder.toString());
	     entero = Integer.parseInt(request.queryParams("entero"));
	     response.status(200);
		}catch(Exception e) {
			e.printStackTrace();
			response.status(400);
		}
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


    
    public static List<ReportEntry> resolver(String uml){
    	
    	 DoctorFisAux doc = new DoctorFisAux(uml.toString());
    	 List<ReportEntry> entries =doc.computeDCRestrictions(null, null);
    	 entries.addAll(doc.computeDCURestrictions(uml));
    	 return entries;
    }
   
   
}
