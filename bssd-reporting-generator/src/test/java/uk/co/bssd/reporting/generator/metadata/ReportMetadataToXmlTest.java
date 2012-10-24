package uk.co.bssd.reporting.generator.metadata;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ReportMetadataToXmlTest {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static final String CHART_TITLE = "Chart Title";
	private static final String CHART_FILENAME = "/Users/auser/home/chart.jpeg";

	private ReportMetadataXmlMarshaller marshaller;
	private ReportMetadata report;

	@Before
	public void before() {
		this.marshaller = new ReportMetadataXmlMarshaller();
		this.report = new ReportMetadata();

		SectionMetadata section = new SectionMetadata();
		this.report.addSection(section);

		ChartMetadata chart = new ChartMetadata(CHART_TITLE, CHART_FILENAME);
		section.addChart(chart);
	}

	@Test
	public void testCanMarshalReportToXml() {
		assertThat(marshalReportToXml(), is(notNullValue()));
	}
	
	@Test
	public void testXmlHasReportMetadataRootElement() {
		String xml = marshalReportToXml();
		assertThat(xml, containsString("<report>"));
		assertThat(xml, endsWith("</report>" + LINE_SEPARATOR));
	}
	
	@Test
	public void testXmlHasSectionsElement() {
		String xml = marshalReportToXml();
		assertThat(xml, containsString("<sections>"));
		assertThat(xml, containsString("</sections"));
	}
	
	@Test
	public void testXmlHasSectionElement() {
		String xml = marshalReportToXml();
		assertThat(xml, containsString("<section>"));
		assertThat(xml, containsString("</section"));
	}
	
	@Test
	public void testXmlHasChartsElement() {
		String xml = marshalReportToXml();
		assertThat(xml, containsString("<charts>"));
		assertThat(xml, containsString("</charts>"));
	}
	
	@Test
	public void testXmlHasChartElement() {
		assertThat(marshalReportToXml(), containsString("<chart title=\"" + CHART_TITLE + "\" filename=\"" + CHART_FILENAME + "\"/>"));
	}
	
	private String marshalReportToXml() {
		return this.marshaller.marshal(this.report);
	}
}