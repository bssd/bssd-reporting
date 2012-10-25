package uk.co.bssd.reporting.pdf;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.helpers.DefaultHandler;

import uk.co.bssd.reporting.metadata.ReportMetadata;
import uk.co.bssd.reporting.metadata.ReportMetadataXmlMarshaller;

public class PdfGenerator {

	private static final String FILENAME_XLST_REPORT_XML_TO_FO = "/uk/co/bssd/reporting/pdf/report-xml-to-fo.xslt";

	public void generatePdf(ReportMetadata reportMetadata, String pdfFilename) {
		Source xmlSource = xmlSource(reportMetadata);
		OutputStream outputStream = outputStream(pdfFilename);
		
		try {
			Result result = result(outputStream);
			transformer().transform(xmlSource, result);
		} catch (TransformerException e) {
			throw new IllegalStateException("Unable to transform report into pdf", e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) { 
				// do nothing
			}
		}
	}

	private Source xmlSource(ReportMetadata reportMetadata) {
		String xml = xmlnput(reportMetadata);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				xml.getBytes());
		return new StreamSource(inputStream);
	}

	private Transformer transformer() {
		InputStream resourceAsStream = PdfGenerator.class
				.getResourceAsStream(FILENAME_XLST_REPORT_XML_TO_FO);
		StreamSource streamSource = new StreamSource(resourceAsStream);
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			return factory.newTransformer(streamSource);
		} catch (TransformerConfigurationException e) {
			throw new IllegalStateException("Unable to construct transformer",
					e);
		}
	}

	private Result result(OutputStream outputStream) {
		DefaultHandler handler = fopSaxHandler(outputStream);
		return new SAXResult(handler);
	}

	private DefaultHandler fopSaxHandler(OutputStream outputStream) {
		try {
			Fop fop = FopFactory.newInstance().newFop(MimeConstants.MIME_PDF,
					outputStream);
			return fop.getDefaultHandler();
		} catch (FOPException e) {
			throw new IllegalStateException(
					"Unable to construct fop sax handler", e);
		}
	}

	private OutputStream outputStream(String pdfFilename) {
		try {
			return new BufferedOutputStream(new FileOutputStream(new File(
					pdfFilename)));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Unable to construct OutputStream",
					e);
		}
	}

	private String xmlnput(ReportMetadata reportMetadata) {
		ReportMetadataXmlMarshaller marshaller = new ReportMetadataXmlMarshaller();
		return marshaller.marshal(reportMetadata);
	}
}