package proyecto;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.Request;
import static spark.Spark.notFound;


import doctor.model.Helio;
import proyecto.controladores.ControladorAlumno;
import proyecto.controladores.ControladorProfesor;
import spark.Response;
import spark.Route;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.staticFileLocation("./");
        port(9000);
        // TODO: add here paths to jars
        Helio.setUrlProviderJar("https://github.com/helio-ecosystem/helio-provider-url/releases/download/v0.1.0/helio-provider-url-0.1.0.jar ");
        Helio.setUrlActionJsonCastJar("https://github.com/helio-ecosystem/helio-action-json-cast/releases/download/v0.1.0/helio-action-json-cast-0.1.0.jar ");
        System.out.println(Helio.getUrlProviderJar());
        System.out.println(Helio.getUrlActionJsonCastJar());
        Helio.loadExternalComponents();
        //stop();

	    get("/conf", ControladorProfesor.setupConfiguration);
	    get("/prof", ControladorProfesor.resolver);
	    get("/alum", ControladorAlumno.resolver);
	    get("/info", ControladorAlumno.gestionar);


        path("/api", () -> {
        	post("/solve-professor", ControladorProfesor.guardar);
        	post("/guardar-archivo-alumno", ControladorAlumno.guardar);
        });
        post("/guardar", ControladorProfesor.guardarNivel);
        get("/guardar", ControladorProfesor.guardarNivel);
        	notFound((Request req, Response res)-> handlex(req, res));

    }
    private static String handlex (Request req, Response res){
    	System.out.println(req.contextPath());
    	return "error";
    }

}
