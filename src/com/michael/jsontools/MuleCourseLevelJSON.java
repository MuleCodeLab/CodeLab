package com.michael.jsontools;

public class MuleCourseLevelJSON
{
	private String title;
	private boolean visible;

	//Constructors
	public MuleCourseLevelJSON()
	{
		this("Course", true);
	}

	public MuleCourseLevelJSON(String title)
	{
		this(title, true);
	}

	public MuleCourseLevelJSON(String title, boolean visible)
	{
		this.title = title;
		this.visible = visible;
	}

	// Tools
	public String toString()
	{
		String out =	"{\n\t"+
						JSONTools.keyValuePair("title", title)+",\n\t"+
						JSONTools.keyValuePair("visible", visible)+"\n}";
		return out;
	}
}