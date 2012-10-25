package uk.co.bssd.reporting.metadata;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class SectionMetadata {

	@XmlElementWrapper(name="charts")
	@XmlElement(name="chart")
	public final List<ChartMetadata> charts;
	
	public SectionMetadata() {
		this.charts = new ArrayList<ChartMetadata>();
	}
	
	public void addChart(ChartMetadata chart) {
		this.charts.add(chart);
	}
}