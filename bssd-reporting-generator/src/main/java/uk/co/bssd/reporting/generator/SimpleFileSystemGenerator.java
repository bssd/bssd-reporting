package uk.co.bssd.reporting.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import uk.co.bssd.reporting.adapter.CsvFileAdapter;
import uk.co.bssd.reporting.adapter.DoubleParser;
import uk.co.bssd.reporting.chart.ChartWriter;
import uk.co.bssd.reporting.chart.JpegChartWriter;
import uk.co.bssd.reporting.chart.TimeSeriesChart;
import uk.co.bssd.reporting.chart.TimeSeriesChartDescriptor;
import uk.co.bssd.reporting.dataset.TimedDatapoints;

public class SimpleFileSystemGenerator {

	public static void main(String[] args) {
		String inputDirectoryName = args[0];
		String outputDirectoryName = args[1];

		File inputDirectory = new File(inputDirectoryName);

		try {
			FileUtils.forceMkdir(new File(outputDirectoryName));
		} catch (IOException e) {
			throw new RuntimeException("Unable to create output directory", e);
		}

		for (File inputFile : inputDirectory.listFiles()) {

			String filename = inputFile.getName();
			String basename = filename.substring(0, filename.lastIndexOf('.'));

			CsvFileAdapter<Double> fileAdapter = new CsvFileAdapter<Double>(
					new DoubleParser(), inputFile);
			TimedDatapoints<Double> timedDatapoints = fileAdapter.adapt();

			TimeSeriesChart<Double> chart = new TimeSeriesChart<Double>(
					new TimeSeriesChartDescriptor(basename, true, basename,
							"Time", "%"), timedDatapoints);

			File outputFile = new File(outputDirectoryName, basename + ".jpeg");
			ChartWriter chartWriter = new JpegChartWriter(outputFile, 640, 480);
			chart.write(chartWriter);
		}
	}
}