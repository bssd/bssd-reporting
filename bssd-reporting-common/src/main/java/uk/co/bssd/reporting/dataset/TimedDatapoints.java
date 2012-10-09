package uk.co.bssd.reporting.dataset;

import java.util.Iterator;
import java.util.List;

public class TimedDatapoints<T> implements Iterable<TimedDatapoint<T>>{

	private final List<TimedDatapoint<T>> timedDatapoints;
	
	TimedDatapoints(List<TimedDatapoint<T>> datapoints) {
		this.timedDatapoints = datapoints;
	}
	
	@Override
	public Iterator<TimedDatapoint<T>> iterator() {
		return this.timedDatapoints.iterator();
	}
}