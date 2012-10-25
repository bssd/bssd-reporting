package uk.co.bssd.reporting.metadata;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ReportMetadataXmlMarshaller {

	private final Marshaller marshaller;

	public ReportMetadataXmlMarshaller() {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ReportMetadata.class);
			this.marshaller = jaxbContext.createMarshaller();
			this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to construct marshaller", e);
		}
	}

	public String marshal(ReportMetadata report) {
		StringWriter writer = new StringWriter();
		try {
			this.marshaller.marshal(report, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unable to marshal to xml", e);
		}
		return writer.toString();
	}
}