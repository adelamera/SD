package business.service;

public class ConcreteCreator {

	public static IExport getExporter(String type) {
		if (type.equals("csv")) {
			return new CSVExport();
		}
		if (type.equals("xml")) {
			return new XMLExport();
		}
		return new XMLExport();
	}

}
