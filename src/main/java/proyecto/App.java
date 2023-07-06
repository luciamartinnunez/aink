package proyecto;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

import doctor.model.Helio;
import proyecto.controladores.ControladorAlumno;
import proyecto.controladores.ControladorProfesor;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.staticFileLocation("./");
        port(9000);
        // TODO: add here paths to jars
        // Helio.setUrlProviderJar(".\\cmp\\helio-provider-url-0.1.0.jar");
        // Helio.setUrlActionJsonCastJar(".\\cmp\\helio-action-json-cast-0.1.0.jar");
        Helio.loadExternalComponents();
        //stop();

	    get("/conf", ControladorProfesor.setupConfiguration);
	    get("/prof", ControladorProfesor.resolver);
	    get("/alum", ControladorAlumno.resolver);

        path("/api", () -> {
        	post("/solve-professor", ControladorProfesor.guardar);
        	post("/guardar-archivo-alumno", ControladorAlumno.guardar);
        });
        post("/guardar", ControladorProfesor.guardarNivel);
        get("/guardar", ControladorProfesor.guardarNivel);


    }
}
