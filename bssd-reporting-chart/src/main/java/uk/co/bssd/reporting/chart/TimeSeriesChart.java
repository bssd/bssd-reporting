package uk.co.bssd.reporting.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import uk.co.bssd.reporting.dataset.TimedDatapoint;
import uk.co.bssd.reporting.dataset.TimedDatapoints;

public class TimeSeriesChart<T extends Number> implements Chart {

	private final XYDataset dataset;
	private final JFreeChart chart;

	public TimeSeriesChart(TimeSeriesChartDescriptor descriptor,
			TimedDatapoints<T> timedDatapoints) {
		this.dataset = convertDataset(descriptor.seriesName(), timedDatapoints);
		this.chart = ChartFactory.createTimeSeriesChart(descriptor.title(),
				descriptor.xAxisLabel(), descriptor.yAxisLabel(), this.dataset,
				descriptor.legend(), false, false);
	}

	@Override
	public void write(ChartWriter writer) {
		writer.write(this.chart);
	}

	private XYDataset convertDataset(String seriesName,
			TimedDatapoints<T> timedDatapoints) {
		TimeSeries series = new TimeSeries(seriesName);
		for (TimedDatapoint<T> timedDatapoint : timedDatapoints) {
			Millisecond regularTimePeriod = new Millisecond(timedDatapoint
					.timestamp().toDate());
			series.add(regularTimePeriod, timedDatapoint.value());
		}
		return new TimeSeriesCollection(series);
	}
}