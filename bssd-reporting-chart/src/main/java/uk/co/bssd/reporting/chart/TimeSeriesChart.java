/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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