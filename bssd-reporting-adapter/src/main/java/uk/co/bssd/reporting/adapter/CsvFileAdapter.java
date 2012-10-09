package uk.co.bssd.reporting.adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import uk.co.bssd.reflection.ClassWrapper;
import uk.co.bssd.reporting.dataset.TimedDatapoint;
import uk.co.bssd.reporting.dataset.TimedDatapoints;
import uk.co.bssd.reporting.dataset.TimedDatapointsBuilder;

public class CsvFileAdapter<T> {

	private static final String SEPERATOR = ",";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

	private final Class<T> clazz;
	private final File file;

	public CsvFileAdapter(Class<T> clazz, File file) {
		this.clazz = clazz;
		this.file = file;
	}

	public TimedDatapoints<T> adapt() {
		TimedDatapointsBuilder<T> builder = TimedDatapointsBuilder
				.aTimedDatapointsBuilder();
		
		for (String line : fileLines()) {
			String[] parts = line.split(SEPERATOR);
			DateTime timestamp = DATE_TIME_FORMATTER.parseDateTime(parts[0]);
			T value = parseValue(parts[1]);
			builder.withTimedDatapoint(new TimedDatapoint<T>(timestamp, value));
		}

		return builder.build();
	}
	
	@SuppressWarnings("unchecked")
	private T parseValue(String value) {
		ClassWrapper classWrapper = ClassWrapper.forClass(this.clazz);
		return (T)classWrapper.constructors().get(1).instantiate(value);
	}
	
	private List<String> fileLines() {
		try {
			return FileUtils.readLines(this.file);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read file", e);
		}
	}
}