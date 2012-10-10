package uk.co.bssd.reporting.adapter;

public class DoubleParser implements ValueParser<Double> {

	@Override
	public Double parse(String value) {
		return Double.valueOf(value);
	}
}