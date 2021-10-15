package com.michael.jsontools;

import java.time.LocalDateTime;

public class MuleHiddenQuestionJSON extends MuleQuestionLevelJSON
{
	private LocalDateTime start;	//Start time for lab session
	private long length;			//Length of the lab session in minutes
	private LocalDateTime pgStart;	//Start date for personal grade evaluation
	private LocalDateTime pgEnd;	//End date for personal grade evaluation (students lose access to question)
	private String group;			//Timetable group for lab session e.g. 'pink'

	//Constructors

	public MuleHiddenQuestionJSON(String title, String course, int labNumber, int questionNumber, String[] files, LocalDateTime start, long length, LocalDateTime pgStart, LocalDateTime pgEnd, String group)
	{
		//super(title, course, labNumber, questionNumber, files);
		setTitle(title);
		setCourse(course);
		setLabNumber(labNumber);
		setQuestionNumber(questionNumber);
		setFiles(files);
		this.start = start;
		this.length = length;
		this.pgStart = pgStart;
		this.pgEnd = pgEnd;
		this.group = group;
		setQid();
	}

	public MuleHiddenQuestionJSON()
	{
		//Meaningless default values
		super();
		this.start = LocalDateTime.of(2021,1,1,0,0);
		this.length = 90;
		this.pgStart = LocalDateTime.of(2021,1,1,0,0);
		this.pgEnd = LocalDateTime.of(2021,1,1,0,0);
		this.group = "red";
		setQid();
	}


	//Getters

	public String getVisibleKeyValue()
	{
		String startStr = JSONTools.formatDateTime(start);
		String endStr = JSONTools.formatDateTime(start.plusMinutes(length));
		String pgStartStr = JSONTools.formatDateTime(pgStart);
		String pgEndStr = JSONTools.formatDateTime(pgEnd);

		return  String.format(
					"(ingroup('demonstrator','lecturer') or ((between(%s,%s) or between(%s,%s)) and (ingroup('%s'))))",
					startStr,
					endStr,
					pgStartStr,
					pgEndStr,
					group
				);
	}

	public String getEvaluateKeyValue()
	{
		String startStr = JSONTools.formatDateTime(start);
		String endStr = JSONTools.formatDateTime(start.plusMinutes(length));

		return	String.format(
					"(((ingroup('demonstrator','lecturer')) or (between(%s,%s)))?'ca':'personal')",
					startStr,
					endStr
				);
	}

	//Setters

	public void setStart(LocalDateTime start)
	{
		this.start = start;
	}

	public void setLength(long length)
	{
		this.length = length;
	}

	public void setPgStart(LocalDateTime pgStart)
	{
		this.pgStart = pgStart;
	}

	public void setPgEnd(LocalDateTime pgEnd)
	{
		this.pgEnd = pgEnd;
	}

	public void setGroup(String group)
	{
		this.group = group;
		setQid();
	}

	public void setQid()
	{
		setQid(
			String.format(
				"%s_l%dq%d-%s",
				numberCharsIn(getCourse()),
				getLabNumber(),
				getQuestionNumber(),
				group
			)
		);
	}

	//Tools

	public String toString()
	{
		String out = super.toString();
		out = out.substring(0, out.length()-2)+",\n"; //removes the '}' from the super String and adds comma to last KVP

		out +=	"\t"+JSONTools.keyValuePair("visible", getVisibleKeyValue())+",\n"+
				"\t"+JSONTools.keyValuePair("Evaluate", getEvaluateKeyValue())+"\n}";

		return out;
	}
}