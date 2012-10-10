package uk.co.bssd.reporting.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class JpegChartWriter implements ChartWriter {

	private final File file;
	private final int height;
	private final int width;

	public JpegChartWriter(String filename, int height, int width) {
		this(new File(filename), height, width);
	}

	public JpegChartWriter(File file, int height, int width) {
		this.file = file;
		this.height = height;
		this.width = width;
	}

	@Override
	public void write(JFreeChart chart) {
		try {
			ChartUtilities.saveChartAsJPEG(this.file, chart, this.height,
					this.width);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to save chart", e);
		}
	}
}