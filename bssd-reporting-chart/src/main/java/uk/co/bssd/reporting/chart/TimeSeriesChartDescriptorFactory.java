package uk.co.bssd.reporting.chart;

public class TimeSeriesChartDescriptorFactory implements ChartDescriptorFactory {

	private final boolean legend;
	private final String seriesName;
	private final String xAxisLabel;
	private final String yAxisLabel;
	
	public TimeSeriesChartDescriptorFactory(boolean legend, String seriesName, String xAxisLabel, String yAxisLabel) {
		this.legend = legend;
		this.seriesName = seriesName;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
	}
	
	@Override
	public ChartDescriptor chartDescriptor(String chartName) {
		return new TimeSeriesChartDescriptor(chartName, this.legend, this.seriesName, this.xAxisLabel, this.yAxisLabel);
	}
}