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
package uk.co.bssd.reporting.chart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilenameFilter {

	private final Pattern pattern;

	public FilenameFilter(String glob) {
		String regex = regexFromGlob(glob);
		this.pattern = Pattern.compile(regex);
	}

	public boolean accept(String filename) {
		Matcher matcher = this.pattern.matcher(filename);
		return matcher.matches();
	}

	private String regexFromGlob(String glob) {
		String out = "^";
		for (int i = 0; i < glob.length(); ++i) {
			final char c = glob.charAt(i);
			switch (c) {
			case '*':
				out += ".*";
				break;
			case '?':
				out += '.';
				break;
			case '.':
				out += "\\.";
				break;
			case '\\':
				out += "\\\\";
				break;
			default:
				out += c;
			}
		}
		return out += '$';
	}
}