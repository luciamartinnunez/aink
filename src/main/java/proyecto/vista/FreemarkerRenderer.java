//package proyecto.vista;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import freemarker.template.Configuration;
//import freemarker.template.Version;
//import spark.ModelAndView;
//import spark.template.freemarker.FreeMarkerEngine;
//
//public class FreemarkerRenderer implements ViewRenderer{
//
//	private FreeMarkerEngine engine;
//	private ObjectMapper mapper;
//
//    private static final String VIEWS_PATH = "./src/main/resources";
//
//    public FreemarkerRenderer() {
//    	Configuration conf= new Configuration(new Version(2,3,31));
//        try {
//			conf.setDirectoryForTemplateLoading(new File(VIEWS_PATH));
//			engine = new FreeMarkerEngine(conf);
//	        mapper = new ObjectMapper();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//    }
//
//	@Override
//    public String render(Map<String,Object> model, String template) throws Exception {
//        String result = null;
//        try{
//        	result = engine.render(new ModelAndView(model, template));
//        }catch(Exception e) {
//            e.printStackTrace();
//            throw new Exception(e.toString());
//        }
//        return result;
//    }
//
//
//	@Override
//	public Map<String, Object> toModel(Object object) {
//		@SuppressWarnings("unchecked")
//		Map<String, Object> model = mapper.convertValue(object, Map.class);
//		return model;
//	}
//
//	@Override
//	public String render(Object object, String template) throws Exception {
//		return render(toModel(object), template);
//	}
//
//	@Override
//	public Map<String, Object> toModel(List<?> object, String variable) {
//		Map<String,Object> model = new HashMap<>();
//		List<Object> values = object.parallelStream().map(elem -> toModel(elem)).collect(Collectors.toList());
//		model.put(variable, values);
//		return model;
//	}
//
//}
