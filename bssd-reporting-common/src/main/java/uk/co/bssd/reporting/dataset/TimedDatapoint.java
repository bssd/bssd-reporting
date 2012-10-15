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