package converter;

public class UnitConverter {
	public double convert(double amount, Unit fromUnit, Unit toUnit) {
		return amount * (fromUnit.getValue() / toUnit.getValue());
	}

	public Unit[] getUnit() {
		return Length.values();
	}

}
