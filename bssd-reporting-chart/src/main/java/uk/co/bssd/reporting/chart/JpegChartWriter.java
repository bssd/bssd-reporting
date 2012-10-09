package uk.co.bssd.reporting.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class JpegChartWriter implements ChartWriter {

	private final String filename;
	private final int height;
	private final int width;

	public JpegChartWriter(String filename, int height, int width) {
		this.filename = filename;
		this.height = height;
		this.width = width;
	}

	@Override
	public void write(JFreeChart chart) {
		try {
			ChartUtilities.saveChartAsJPEG(new File(this.filename), chart,
					this.height, this.width);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to save chart", e);
		}
	}
}