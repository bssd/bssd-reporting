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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import uk.co.bssd.reporting.dataset.TimedDatapoints;

public class CsvFileAdapterTest {

	private static final String FILENAME_CSV = "/example-csv-file.csv";

	private CsvFileAdapter<Double> adapter;

	@Before
	public void before() throws Exception {
		File file = new File(getClass().getResource(FILENAME_CSV).toURI());
		this.adapter = new CsvFileAdapter<Double>(new DoubleParser(), file);
	}

	@Test
	public void testReturnedDatasetIsNotNull() {
		assertThat(this.adapter.adapt(), is(notNullValue()));
	}

	@Test
	public void testReturnedDatasetHasCorrectNumberOfDatapoints() {
		assertThat(this.adapter.adapt().size(), is(4));
	}

	@Test
	public void testTimestampsAreCorrect() {
		TimedDatapoints<Double> timedDatapoints = this.adapter.adapt();
		assertThat(timedDatapoints.iterator().next().timestamp(),
				is(new DateTime(2012, 10, 8, 17, 26)));
	}
	
	@Test
	public void testValuesAreCorrect() {
		TimedDatapoints<Double> timedDatapoints = this.adapter.adapt();
		assertThat(timedDatapoints.iterator().next().value(), is(1.0));
	}
}