package proyecto;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.path;

import proyecto.controladores.ControladorAlumno;
import proyecto.controladores.ControladorProfesor;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.staticFileLocation("./");
        port(9000);
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
