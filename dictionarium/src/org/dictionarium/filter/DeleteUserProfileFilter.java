package org.dictionarium.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "deleteUserProfileFilter",
urlPatterns = {"/deleteUserProfile/*"})

public class DeleteUserProfileFilter extends BaseFilter {

	public DeleteUserProfileFilter() {
		super();
	}
}
