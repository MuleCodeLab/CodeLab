package com.michael.jsontools;


import java.time.LocalDateTime;

public class TestHiddenQ
{
	public static void main(String[] args)
	{
		MuleHiddenQuestionJSON json = new MuleHiddenQuestionJSON(
				"Prime Palindrome",
				"CS162",
				6,
				6,
				new String[] {"PrimePalindrome.java"},
				LocalDateTime.of(2021,2,12,11,30),
				90,
				LocalDateTime.of(2021,2,12,23,5),
				LocalDateTime.of(2021,8,30,23,0),
				"pink"
			);

		/*MuleHiddenQuestionJSON json = new MuleHiddenQuestionJSON();
		json.setTitle("Prime Palindrome-pink");
		json.setCourse("CS162");
		json.setLNumber(9);
		json.setQNumber(11);
		json.setFiles("PrimePalindrome.java");
		json.setStart(LocalDateTime.of(2021,2,12,11,30));
		json.setLength(90);
		json.setPgStart(LocalDateTime.of(2021,2,12,23,5));
		json.setPgEnd(LocalDateTime.of(2021,8,30,23,0));
		json.setGroup("pink");*/


		System.out.println(json);
	}
}