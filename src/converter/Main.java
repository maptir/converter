package converter;

public class Main {
	public static void main(String[] args) {
		UnitConverter convert = new UnitConverter();
		ConverterUI ui = new ConverterUI(convert);
		ui.run();
	}
}
