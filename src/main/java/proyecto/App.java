package proyecto;

import static spark.Spark.*;
import static spark.Spark.port;

import proyecto.controladores.ControladorSaludo;
import proyecto.controladores.Controlador_profesor;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.staticFileLocation("./");
        port(9000);
        get("/hello", ControladorSaludo.saluda);
        get("/hello2", ControladorSaludo.saluda2);
        get("/home", ControladorSaludo.menu);
        //stop();
        get("/api/conf", Controlador_profesor.setupConfiguration);

    }

}
