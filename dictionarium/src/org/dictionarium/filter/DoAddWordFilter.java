package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "doAddWordFilter", urlPatterns = {"/doAddWord/*"})

public class DoAddWordFilter extends BaseFilter {
	
	public DoAddWordFilter() {
		super();
	}
}
