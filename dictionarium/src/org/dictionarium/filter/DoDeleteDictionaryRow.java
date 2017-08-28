package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "doDeleteDictionaryRowFilter",
urlPatterns = {"/doDeleteDictionaryRow/*"})


public class DoDeleteDictionaryRow extends BaseFilter {

	public DoDeleteDictionaryRow() {
		super();
	}
}
