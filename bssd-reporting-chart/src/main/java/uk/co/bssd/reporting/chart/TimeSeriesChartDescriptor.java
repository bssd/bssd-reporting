package uk.co.bssd.reporting.chart;

public class TimeSeriesChartDescriptor extends ChartDescriptor {

	private final String seriesName;
	private final String xAxisLabel;
	private final String yAxisLabel;
	
	public TimeSeriesChartDescriptor(String title, boolean legend, String seriesName, String xAxisLabel, String yAxisLabel) {
		super(title, legend);
		this.seriesName = seriesName;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
	}
	
	public String seriesName() {
		return this.seriesName;
	}
	
	public String xAxisLabel() {
		return this.xAxisLabel;
	}
	
	public String yAxisLabel() {
		return this.yAxisLabel;
	}
}