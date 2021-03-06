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
package uk.co.bssd.reporting.metadata;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class ChartMetadata {

	@XmlAttribute(name="title")
	private String title;
	
	@XmlAttribute(name="filename")
	private String filename;
	
	/** 
	 * For Jaxb's benefit.
	 */
	private ChartMetadata() {
		super();
	}
	
	public ChartMetadata(String title, String filename) {
		this();
		this.title = title;
		this.filename = filename;
	}
	
	public String title() {
		return this.title;
	}
	
	public String filename() {
		return this.filename;
	}
}