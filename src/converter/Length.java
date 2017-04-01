package converter;

public enum Length implements Unit {
	METER("METER", 1.0), KILOMETER("KILOMETER", 1000), CENTIMETER("CENTIMETER", 0.01), MILE("MILE",
			1609.344), FOOT("FOOT", 0.3048), WA("WA", 2);

	public final String name;
	public final double value;

	Length(String name, double value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public double getValue() {
		return value;
	}

	public String toString() {
		return name;
	}
}
