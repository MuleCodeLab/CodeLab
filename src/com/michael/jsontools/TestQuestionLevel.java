package com.michael.jsontools;

public class TestQuestionLevel
{
	public static void main(String[] args)
	{
		MuleQuestionLevelJSON json = new MuleQuestionLevelJSON();
		json.setTitle("Test Question");
		json.setCourse("CS161");
		json.setLabNumber(1);
		json.setQuestionNumber(12);
		String[] files = {"Main.java", "Book.java", "Novel.java", "Textbook.java"};
		json.setFiles(files);

		System.out.println(json);
	}
}