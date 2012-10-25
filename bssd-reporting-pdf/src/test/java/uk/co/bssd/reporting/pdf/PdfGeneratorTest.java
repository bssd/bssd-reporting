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
package uk.co.bssd.reporting.pdf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import uk.co.bssd.reporting.metadata.ChartMetadata;
import uk.co.bssd.reporting.metadata.ReportMetadata;
import uk.co.bssd.reporting.metadata.SectionMetadata;

public class PdfGeneratorTest {

	private static final String CHART_TITLE = "Chart";
	private static final String CHART_FILENAME = "src/test/resources/chart.jpeg";

	private static final String PDF_FILENAME = "target/"
			+ UUID.randomUUID().toString() + ".pdf";

	private ReportMetadata reportMetadata;

	private PdfGenerator pdfGenerator;

	@Before
	public void before() {
		assertThat(pdfFile().exists(), is(false));

		this.pdfGenerator = new PdfGenerator();

		this.reportMetadata = new ReportMetadata();

		SectionMetadata section = new SectionMetadata();
		this.reportMetadata.addSection(section);

		ChartMetadata chart = new ChartMetadata(CHART_TITLE, CHART_FILENAME);
		section.addChart(chart);
	}

	@Test
	public void testPdfFileIsGenerated() {
		this.pdfGenerator.generatePdf(this.reportMetadata, PDF_FILENAME);
		assertThat("The pdf file should have been generated", pdfFile()
				.exists(), is(true));
	}

	private File pdfFile() {
		return new File(PDF_FILENAME);
	}
}