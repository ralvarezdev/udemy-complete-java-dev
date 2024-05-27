package oop.abstraction.interfaces;

public interface Mappable {
	String JSON_PROPERTY = """
			"property": {%s}""";

	static void mapIt(Mappable mappable) {
		System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
	}

	default String toJSON() {
		return """
				"type": "%s", "label": "%s", "marker": "%s"
				""".formatted(getShape(), getLabel(), getMarker());
	};

	abstract String getLabel();

	abstract String getMarker();

	abstract Geometry getShape();
}
