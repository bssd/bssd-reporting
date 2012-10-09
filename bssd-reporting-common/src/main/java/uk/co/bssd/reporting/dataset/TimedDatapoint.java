package uk.co.bssd.reporting.dataset;

import org.joda.time.DateTime;

public class TimedDatapoint<T> {

	private final DateTime timestamp;
	private final T value;
	
	public TimedDatapoint(DateTime timestamp, T value) {
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public DateTime timestamp() {
		return this.timestamp;
	}
	
	public T value() {
		return this.value;
	}
}