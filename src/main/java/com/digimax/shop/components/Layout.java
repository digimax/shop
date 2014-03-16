package com.digimax.shop.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

/**
 * Layout component for pages of application test-project.
 */
@Import(
        stylesheet = {"context:css/bootswatch.less", "context:css/site.css"},
        library = {"context:mybootstrap/dist/js/bootstrap.js"})
public class Layout
{
	@Inject
	private ComponentResources resources;

	/**
	 * The page title, for the <title> element and the <h1> element.
	 */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	@Property
	@Inject
	@Symbol(SymbolConstants.APPLICATION_VERSION)
	private String appVersion;



	public String getClassForPageName()
	{
		return resources.getPageName().equalsIgnoreCase(pageName)
				? "active"
				: null;
	}

	public String[] getPageNames()
	{
		return new String[]{"Index", "About", "Contact"};
	}

//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css
//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css
////netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js

}
