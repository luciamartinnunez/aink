package proyecto.controladores;
import java.util.HashMap;
import java.util.Map;

import proyecto.vista.FreemarkerRenderer;
import proyecto.vista.ViewRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControladorSaludo {
    public static Route saluda=(Request request, Response response)->{
        String name=request.queryMap("name").value();
        return "hola" +name;
    };
    public static Route saluda2=(Request request, Response response)->{
        ViewRenderer view= new FreemarkerRenderer();
        String result="";
        Map<String, Object> attr= new HashMap<>();
        //return new ModelAndView(attr, "hola.ftl");
        return view.render(attr, "hola.ftl");
    };
    public static Route menu=(Request request, Response response)->{
        ViewRenderer view= new FreemarkerRenderer();
        Map<String, Object> attr= new HashMap<>();
        return view.render(attr, "home.ftl");};
}
