package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "userProfileFilter", urlPatterns = {"/userProfile/*"})

public class UserProfileFilter extends BaseFilter {

	public UserProfileFilter() {
		super();
	}
}
