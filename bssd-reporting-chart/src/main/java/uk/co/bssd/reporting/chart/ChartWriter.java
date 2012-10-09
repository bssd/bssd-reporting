package uk.co.bssd.reporting.chart;

import org.jfree.chart.JFreeChart;

public interface ChartWriter {

	void write(JFreeChart chart);
}