package uk.co.bssd.reporting.metadata;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="report")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportMetadata {

	@XmlElementWrapper(name="sections")
	@XmlElement(name="section")
	private final List<SectionMetadata> sections;
	
	public ReportMetadata() {
		this.sections = new ArrayList<SectionMetadata>();
	}
	
	public void addSection(SectionMetadata section) {
		this.sections.add(section);
	}
}