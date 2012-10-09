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
		this.adapter = new CsvFileAdapter<Double>(Double.class, file);
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