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

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class PngChartWriter implements ChartWriter {

	private final File file;
	private final int height;
	private final int width;

	public PngChartWriter(String filename, int height, int width) {
		this(new File(filename), height, width);
	}

	public PngChartWriter(File file, int height, int width) {
		this.file = file;
		this.height = height;
		this.width = width;
	}

	@Override
	public void write(JFreeChart chart) {
		try {
			ChartUtilities.saveChartAsPNG(this.file, chart, this.width,
					this.height);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to save chart", e);
		}
	}
}