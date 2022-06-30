package com.graphql.pojo;

public class GQLquery {

	private String Query;
	private Object variables;
	
	public String getQuery()
	{
		return Query;
	}
	
	public void setQuery(String Query)
	{
		this.Query=Query;
	}

	public Object getVariables() {
		return variables;
	}

	public void setVariables(Object variables) {
		this.variables = variables;
	}
}
