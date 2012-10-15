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