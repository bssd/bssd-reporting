package uk.co.bssd.reporting.adapter;

public interface ValueParser<T> {

	T parse(String value);
}