package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "signOutFilter", urlPatterns = {"/signOut/*"})

public class SignOutFilter extends BaseFilter{

	public SignOutFilter() {
		super();
	}
}
