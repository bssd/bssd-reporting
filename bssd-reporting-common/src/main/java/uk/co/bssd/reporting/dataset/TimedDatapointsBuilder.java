package uk.co.bssd.reporting.dataset;

import java.util.ArrayList;
import java.util.List;

public class TimedDatapointsBuilder<T> {

	private final List<TimedDatapoint<T>> datapoints;
	
	public static <T> TimedDatapointsBuilder<T> aTimedDatapointsBuilder() {
		return new TimedDatapointsBuilder<T>();
	}
	
	private TimedDatapointsBuilder() {
		this.datapoints = new ArrayList<TimedDatapoint<T>>();
	}
	
	public TimedDatapointsBuilder<T> withTimedDatapoint(TimedDatapoint<T> datapoint) {
		this.datapoints.add(datapoint);
		return this;
	}
	
	public TimedDatapoints<T> build() {
		return new TimedDatapoints<T>(this.datapoints);
	}
}