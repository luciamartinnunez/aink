package proyecto;

import static spark.Spark.*;
import static spark.Spark.port;

import proyecto.controladores.ControladorAlumno;
import proyecto.controladores.ControladorProfesor;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.staticFileLocation("./");
        port(9000);
        //stop();
        get("/conf", ControladorProfesor.setupConfiguration);
        get("/prof/resol", ControladorProfesor.resolver);
        get("/api/alum/resol", ControladorAlumno.resolver);

    }

}
