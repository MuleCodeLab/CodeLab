package com.michael.jsontools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class JSONTools
{
	//JSON items

	public static String array(String[] values)
	{
		//Returns JSON array of type String e.g. <["Value1", "Value2", "Value3"]>
		String out = "[";

		for(int i = 0; i < values.length; i++)
		{
			out+= "\""+values[i]+"\"";
			if(i < values.length-1)
				out += ", ";
		}
		out += "]";

		return out;
	}

	public static String array(String s1, String s2)
	{
		String[] a = {s1, s2};
		return array(a);
	}

	public static String array(String s1, String s2, String s3)
	{
		String[] a = {s1, s2, s3};
		return array(a);
	}

	public static String array(String s1, String s2, String s3, String s4)
	{
		String[] a = {s1, s2, s3, s4};
		return array(a);
	}

	public static String array(String s1, String s2, String s3, String s4, String s5)
	{
		String[] a = {s1, s2, s3, s4, s5};
		return array(a);
	}


	public static String keyValuePair(String key, String value)
	{
		//Returns JSON key-value where value is String e.g. <"key": "value">
		return String.format("\"%s\": \"%s\"", key, value);
	}

	public static String keyValuePairWithoutQuotes(String key, String value)
	{
		//Returns JSON key-value where value has no quotes e.g. <"key": value>
		return String.format("\"%s\": %s", key, value);
	}

	public static String keyValuePair(String key, boolean value)
	{
		//e.g. <"visible": true>
		return keyValuePairWithoutQuotes(key, String.valueOf(value));
	}

	public static String keyValuePair(String key, int value)
	{
		//e.g. <"number": 1>
		return keyValuePairWithoutQuotes(key, String.valueOf(value));
	}


	//MULE conditions

	public static String condition(String condition, String value)
	{
		//Returns a MULE condition e.g. "hasRole('lecturer')"
		return String.format("%s(%s)", condition, value);
	}

	public static String condition(String condition, String[] values)
	{
		//Returns a MULE condition with multiple values e.g. "hasRole('lecturer', 'demonstrator')"
		String out = condition+"(";
		for(int i = 0; i < values.length; i++)
		{
			out += values[i];
			if(i < values.length-1)
				out += ",";
		}
		return out+")";
	}

	public static String condition(String condition, String value1, String value2)
	{
		String[] values = {value1, value2};
		return condition(condition, values);
	}

	public static String condition(String condition, String value1, String value2, String value3)
	{
		String[] values = {value1, value2, value3};
		return condition(condition, values);
	}

	public static String condition(String condition, String value1, String value2, String value3, String value4)
	{
		String[] values = {value1, value2, value3, value4};
		return condition(condition, values);
	}


	public static String betweenAny(LocalDateTime[] starts, LocalDateTime[] ends)
	{
		//The metadata.json file requires some of the key-values to multiple "between(X,Y)"
		//conditions connected by a logical "or". X represents the start time and Y repesents the end of each time period.
		//This method will return a String of these conditions where 'starts' are the X dates and 'ends' are the Y dates.
		//E.g. "between('June 2 2021 09:00','June 2 2021 10:00') or between('June 2 2021 11:00','June 2 2021 12:00')..."

		String out = condition("between", formatDateTime(starts[0]), formatDateTime(ends[0]));
		if(starts.length > 1)
		{
			for(int i = 1; i < starts.length; i++)
			{
				out += " or "+condition("between", formatDateTime(starts[i]), formatDateTime(ends[i]));			}
		}
		return out;
	}

	public static String formatDateTime(LocalDateTime ldt)
	{
		//Returns a String containing a date in the MULE format e.g. 'June 2 2021 09:10'
		DateTimeFormatter muleTime = DateTimeFormatter.ofPattern("MMMM d yyyy HH:mm");
		return "'"+ldt.format(muleTime)+"'";
	}

}