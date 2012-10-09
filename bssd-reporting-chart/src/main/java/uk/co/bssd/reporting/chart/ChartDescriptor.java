package uk.co.bssd.reporting.chart;

public abstract class ChartDescriptor {

	private final String title;
	private final boolean legend;
	
	public ChartDescriptor(String title, boolean legend) {
		this.title = title;
		this.legend = legend;
	}
	
	public String title() {
		return this.title;
	}
	
	public boolean legend() {
		return this.legend;
	}
}