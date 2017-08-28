package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "addWordFilter", urlPatterns = {"/addWord/*"})

public class AddWordFilter extends BaseFilter {
	
	public AddWordFilter() {
		super();
	}
}
