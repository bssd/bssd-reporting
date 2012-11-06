package uk.co.bssd.reporting.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartDescriptorTemplates {

	private final List<ChartDescriptorTemplate> templates;
	
	public ChartDescriptorTemplates() {
		this.templates = new ArrayList<ChartDescriptorTemplate>();
	}
	
	public void addTemplate(ChartDescriptorTemplate template) {
		this.templates.add(template);
	}
	
	public ChartDescriptorTemplate findTemplate(String filename) {
		for (ChartDescriptorTemplate template : this.templates) {
			if (template.accept(filename)) {
				return template;
			}
		}
		throw new IllegalStateException("Unable to find template for filename '" + filename + "'");
	}
}