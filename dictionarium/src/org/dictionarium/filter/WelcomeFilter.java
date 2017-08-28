package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "welcomeFilter", urlPatterns = {"/welcome/*"})

public class WelcomeFilter extends BaseFilter {

	public WelcomeFilter() {
		super();
	}
}
