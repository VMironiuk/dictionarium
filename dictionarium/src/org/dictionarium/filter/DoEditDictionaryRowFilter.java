package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "doEditDictionaryRowFilter",
urlPatterns = {"/doEditDictionaryRow/*"})

public class DoEditDictionaryRowFilter extends BaseFilter {

	public DoEditDictionaryRowFilter() {
		super();
	}
}
