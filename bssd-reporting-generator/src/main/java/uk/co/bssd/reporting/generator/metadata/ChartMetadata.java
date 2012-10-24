package uk.co.bssd.reporting.generator.metadata;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class ChartMetadata {

	@XmlAttribute(name="title")
	private String title;
	
	@XmlAttribute(name="filename")
	private String filename;
	
	/** 
	 * For Jaxb's benefit.
	 */
	private ChartMetadata() {
		super();
	}
	
	public ChartMetadata(String title, String filename) {
		this();
		this.title = title;
		this.filename = filename;
	}
	
	public String title() {
		return this.title;
	}
	
	public String filename() {
		return this.filename;
	}
}