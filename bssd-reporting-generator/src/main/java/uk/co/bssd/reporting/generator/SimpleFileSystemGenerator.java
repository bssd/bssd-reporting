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
package uk.co.bssd.reporting.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import uk.co.bssd.reporting.adapter.CsvFileAdapter;
import uk.co.bssd.reporting.adapter.DoubleParser;
import uk.co.bssd.reporting.chart.ChartDescriptorTemplate;
import uk.co.bssd.reporting.chart.ChartDescriptorTemplates;
import uk.co.bssd.reporting.chart.ChartWriter;
import uk.co.bssd.reporting.chart.PngChartWriter;
import uk.co.bssd.reporting.chart.TimeSeriesChart;
import uk.co.bssd.reporting.chart.TimeSeriesChartDescriptor;
import uk.co.bssd.reporting.chart.TimeSeriesChartDescriptorFactory;
import uk.co.bssd.reporting.dataset.TimedDatapoints;
import uk.co.bssd.reporting.metadata.ChartMetadata;
import uk.co.bssd.reporting.metadata.ReportMetadata;
import uk.co.bssd.reporting.metadata.SectionMetadata;
import uk.co.bssd.reporting.pdf.PdfGenerator;

public class SimpleFileSystemGenerator {

	public static void main(String[] args) {
		String inputDirectoryName = args[0];
		String outputDirectoryName = args[1];

		File inputDirectory = new File(inputDirectoryName);

		createOutputDirectory(outputDirectoryName);

		ReportMetadata reportMetadata = new ReportMetadata();
		SectionMetadata section = new SectionMetadata();
		reportMetadata.addSection(section);

		ChartDescriptorTemplates chartDescriptorTemplates = chartDescriptorTemplates();

		for (File inputFile : inputDirectory.listFiles()) {
			String filename = inputFile.getName();
			String basename = filename.substring(0, filename.lastIndexOf('.'));

			CsvFileAdapter<Double> fileAdapter = new CsvFileAdapter<Double>(
					new DoubleParser(), inputFile);
			TimedDatapoints<Double> timedDatapoints = fileAdapter.adapt();

			TimeSeriesChartDescriptor chartDescriptor = (TimeSeriesChartDescriptor) chartDescriptorTemplates
					.findTemplate(basename).chartDescriptorFactory()
					.chartDescriptor(basename);
			TimeSeriesChart<Double> chart = new TimeSeriesChart<Double>(
					chartDescriptor, timedDatapoints);

			File outputFile = new File(outputDirectoryName, basename + ".png");
			ChartWriter chartWriter = new PngChartWriter(outputFile, 480, 640);
			chart.write(chartWriter);

			ChartMetadata chartMetadata = new ChartMetadata(basename,
					outputFile.getAbsolutePath());
			section.addChart(chartMetadata);
		}

		PdfGenerator pdfGenerator = new PdfGenerator();
		pdfGenerator.generatePdf(reportMetadata, outputDirectoryName
				+ "/report.pdf");
	}

	private static void createOutputDirectory(String outputDirectoryName) {
		try {
			FileUtils.forceMkdir(new File(outputDirectoryName));
		} catch (IOException e) {
			throw new RuntimeException("Unable to create output directory", e);
		}
	}

	private static ChartDescriptorTemplates chartDescriptorTemplates() {
		ChartDescriptorTemplates templates = new ChartDescriptorTemplates();
		templates.addTemplate(new ChartDescriptorTemplate("*cpu*",
				new TimeSeriesChartDescriptorFactory(true, "Cpu Usage", "Time",
						"%")));
		templates.addTemplate(new ChartDescriptorTemplate("*heap*",
				new TimeSeriesChartDescriptorFactory(true, "Heap Usage",
						"Time", "bytes")));
		return templates;
	}
}