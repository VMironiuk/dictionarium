package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "dictionaryFilter", urlPatterns = {"/dictionary/*"})

public class DictionaryFilter extends BaseFilter {
	
	public DictionaryFilter() {
		super();
	}
}
