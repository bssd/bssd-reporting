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
package uk.co.bssd.reporting.adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import uk.co.bssd.reporting.dataset.TimedDatapoint;
import uk.co.bssd.reporting.dataset.TimedDatapoints;
import uk.co.bssd.reporting.dataset.TimedDatapointsBuilder;

public class CsvFileAdapter<T> {

	private static final String SEPERATOR = ",";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

	private final File file;
	private final ValueParser<T> valueParser;

	public CsvFileAdapter(ValueParser<T> parser, File file) {
		this.valueParser = parser;
		this.file = file;
	}

	public TimedDatapoints<T> adapt() {
		TimedDatapointsBuilder<T> builder = TimedDatapointsBuilder
				.aTimedDatapointsBuilder();
		
		for (String line : fileLines()) {
			String[] parts = line.split(SEPERATOR);
			DateTime timestamp = DATE_TIME_FORMATTER.parseDateTime(parts[0]);
			T value = this.valueParser.parse(parts[1]);
			builder.withTimedDatapoint(new TimedDatapoint<T>(timestamp, value));
		}

		return builder.build();
	}
	
	private List<String> fileLines() {
		try {
			return FileUtils.readLines(this.file);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read file", e);
		}
	}
}