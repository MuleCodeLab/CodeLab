package com.michael.jsontools;


import java.time.LocalDateTime;

public class TestLabLevel
{
	public static void main(String[] args)
	{
		MuleLabLevelJSON json = new MuleLabLevelJSON();
		json.setLabel("Lab");
		json.setLabNumber(1);
		json.setAccessStart(LocalDateTime.of(2021, 2, 11, 9, 0));
		json.setAccessEnd(LocalDateTime.of(2021, 8, 30, 23, 59));
		json.setCaEvalStart(LocalDateTime.of(2021, 2, 11, 10, 15));
		json.setCaEvalEnd(LocalDateTime.of(2021, 2, 18, 23, 5));

		System.out.println(json);
	}
}