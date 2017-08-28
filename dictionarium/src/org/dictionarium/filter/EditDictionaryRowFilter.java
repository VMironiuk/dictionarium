package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "editDictionaryRowFilter",
urlPatterns = {"/editDictionaryRow/*"})

public class EditDictionaryRowFilter extends BaseFilter {

	public EditDictionaryRowFilter() {
		super();
	}
}
