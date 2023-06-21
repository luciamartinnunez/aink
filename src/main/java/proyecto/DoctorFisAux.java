package proyecto;

import java.io.IOException;
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

import org.apache.jena.ext.com.google.common.collect.Lists;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import doctor.DoctorFIS;
import doctor.Utils;
import doctor.model.Helio;
import doctor.model.report.Level;
import doctor.model.report.ReportEntry;
import doctor.model.restrictions.Restriction;
import doctor.model.restrictions.dc.DC01;
import doctor.model.restrictions.dc.DC02;
import doctor.model.restrictions.dc.DC03;
import doctor.model.restrictions.dc.DC04;
import doctor.model.restrictions.dc.DC05;
import doctor.model.restrictions.dc.DC06;
import doctor.model.restrictions.dc.DC10;
import doctor.model.restrictions.dc.DC11;
import doctor.model.restrictions.dc.DC12;
import doctor.model.restrictions.dc.DC13;
import doctor.model.restrictions.dc.DC13a;
import doctor.model.restrictions.dcu.DCU01;
import doctor.model.restrictions.dcu.DCU02;
import doctor.model.restrictions.dcu.DCU03;
import doctor.model.restrictions.dcu.DCU04;
import doctor.model.restrictions.dcu.DCU05;
import doctor.model.restrictions.dcu.DCU06;
import doctor.model.restrictions.dcu.DCU07;
import doctor.model.restrictions.dcu.DCU08;
import doctor.model.restrictions.dcu.DCU10;
import doctor.model.restrictions.dcu.DCU11;
import doctor.model.restrictions.dcu.DCU12;
import doctor.model.restrictions.dcu.DCU13;
import doctor.model.restrictions.dcu.DCU14;
import doctor.model.restrictions.dcu.DCU17;
import helio.blueprints.TranslationUnit;
import helio.blueprints.UnitBuilder;
import helio.builder.siot.rx.SIoTRxBuilder;

public class DoctorFisAux implements DoctorFIS {

	private Model model = ModelFactory.createDefaultModel();
	
	public DoctorFisAux(String uml) {
		this.model = toRDF(uml);
	}
	
	// Ancillary: transformation methods
	
	 public static Model toRDF(String uml) {
			Model model = ModelFactory.createDefaultModel();
			Map<String,Object> localVariables = new HashMap<>();
			localVariables.put("umlRaw", uml);

			UnitBuilder builder = new SIoTRxBuilder();
			try {
				Set<TranslationUnit> list = builder.parseMapping(readFile("C:\\Users\\marti\\Desktop\\PIE\\Actual\\doctorFIS\\cmp\\ConnectorsDoctorFis.ftl"));
				list.addAll(builder.parseMapping(readFile(".\\cmp\\ElementsDoctorFis.ftl")));
				list.addAll(builder.parseMapping(readFile(".\\cmp\\ModelDoctorFis.ftl")));
//				list.stream().map(unit -> processUnit(unit, localVariables))
//				                     .forEach(result ->  System.out.println(result));
				list.stream().map(unit -> processUnit(unit, localVariables))
	            .map(result ->  Utils.readModel(result, "turtle"))
	            .forEach(modelAux -> model.add(modelAux));
			}catch(Exception e) {
				e.printStackTrace();
			}
			return model;
		}

	    private static String readFile (String dir) throws IOException {

	        return Utils.readFile(dir);
	    }
	    private static ExecutorService service = Executors.newScheduledThreadPool(10);

	    private static String processUnit(TranslationUnit unit, Map<String,Object> localVariables) {
			String result = "";
			try {
				Future<String> f = service.submit(unit.getTask(localVariables));
				result = f.get();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	
	//
	
	@Override
	public List<ReportEntry> computeDCRestrictions(String uml, Level level){
		
		List<ReportEntry> entries = Lists.newArrayList();
		for (int index = 0; index < restrictionsDC.size(); index++) {
			Restriction res = restrictionsDC.get(index);
			res.evaluate(model).forEach(elem -> entries.add(elem) );
		}
		return entries;
	}
	
	@Override
	public List<ReportEntry> computeDCURestrictions(String uml){
		List<ReportEntry> entries = Lists.newArrayList();
		for (int index = 0; index < restrictionsDCU.size(); index++) {
			Restriction res = restrictionsDCU.get(index);
			res.evaluate(model).forEach(elem -> entries.add(elem) );
		}
		return entries;
	}
	
	 public static List<Restriction> restrictionsDC = new ArrayList<>();
		public static List<Restriction> restrictionsDCU = new ArrayList<>();
		static {

			restrictionsDCU.add(new DCU01());
			restrictionsDCU.add(new DCU02());
			restrictionsDCU.add(new DCU03());
			restrictionsDCU.add(new DCU04());
			restrictionsDCU.add(new DCU05());
			restrictionsDCU.add(new DCU06());
			restrictionsDCU.add(new DCU07());
			restrictionsDCU.add(new DCU08());
			// restrictions.add(new DCU09());
			restrictionsDCU.add(new DCU10());
			restrictionsDCU.add(new DCU11());
			restrictionsDCU.add(new DCU12());
			restrictionsDCU.add(new DCU13());
			restrictionsDCU.add(new DCU13());
			restrictionsDCU.add(new DCU14());
			restrictionsDCU.add(new DCU17());
			restrictionsDC.add(new DC01());
			restrictionsDC.add(new DC02());
			restrictionsDC.add(new DC03());
			restrictionsDC.add(new DC04());
			restrictionsDC.add(new DC05());
			restrictionsDC.add(new DC06());
			restrictionsDC.add(new DC10());
			restrictionsDC.add(new DC11());
			restrictionsDC.add(new DC12());
			restrictionsDC.add(new DC13());
			restrictionsDC.add(new DC13a());
		}

}
