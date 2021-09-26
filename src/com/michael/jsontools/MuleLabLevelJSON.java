package com.michael.jsontools;


import java.time.LocalDateTime;

public class MuleLabLevelJSON
{
	private String label;				//The term used to describe this problem-set e.g. "Lab", "Week", "Problem Set", "Tutorial"
	int labNumber;
	private LocalDateTime accessStart;	//Start of access to lab
	private LocalDateTime accessEnd;	//End of access to lab
	private LocalDateTime caEvalStart;  //Start of period of CA evaluation
	private LocalDateTime caEvalEnd;	//End of period of CA evaluation


	//Constructors

	public MuleLabLevelJSON(String label, int labNumber, LocalDateTime accessStart, LocalDateTime accessEnd, LocalDateTime caEvalStart, LocalDateTime caEvalEnd)
	{
		this.label = label;
		this.labNumber = labNumber;
		this.accessStart = accessStart;
		this.accessEnd = accessEnd;
		this.caEvalStart = caEvalStart;
		this.caEvalEnd = caEvalEnd;
	}

	public MuleLabLevelJSON()
	{
		//Meaningless default values
		label = "DEF_TITLE";
		this.labNumber = 0;
		accessStart = LocalDateTime.of(2021,1,1,0,0);
		accessEnd = LocalDateTime.of(2021,1,1,0,0);
		caEvalStart = LocalDateTime.of(2021,1,1,0,0);
		caEvalEnd = LocalDateTime.of(2021,1,1,0,0);
	}


	//Getters

	public String getEvaluateKeyValue()
	{
		String start = JSONTools.formatDateTime(caEvalStart);
		String end = JSONTools.formatDateTime(caEvalEnd);
		return String.format("((ingroup('demonstrator','lecturer') or between(%s,%s))?'ca':'personal')", start, end);
	}

	public String getVisibleKeyValue()
	{
		String start = JSONTools.formatDateTime(accessStart);
		String end = JSONTools.formatDateTime(accessEnd);
		return String.format("(ingroup('demonstrator','lecturer') or between(%s,%s))", start, end);
	}

	//Setters

	public void setLabel(String label)
	{
		this.label = label;
	}

	public void setLabNumber(int labNumber)
	{
		this.labNumber = labNumber;
	}

	public void setAccessStart(LocalDateTime accessStart)
	{
		this.accessStart = accessStart;
	}

	public void setAccessEnd(LocalDateTime accessEnd)
	{
		this.accessEnd = accessEnd;
	}

	public void setCaEvalStart(LocalDateTime caEvalStart)
	{
		this.caEvalStart = caEvalStart;
	}

	public void setCaEvalEnd(LocalDateTime caEvalEnd)
	{
		this.caEvalEnd = caEvalEnd;
	}


	//Tools

	public String toString()
	{
		return  "{\n"+
				"\t"+JSONTools.keyValuePair("title", String.format("%s %02d", label, labNumber))+",\n"+
				"\t"+JSONTools.keyValuePair("Run", true)+",\n"+
				"\t"+JSONTools.keyValuePair("visible", getVisibleKeyValue())+",\n"+
				"\t"+JSONTools.keyValuePair("Evaluate", getEvaluateKeyValue())+",\n"+
				"\t"+JSONTools.keyValuePair("copy", "(((ingroup('demonstrator','lecturer')))?'workbook':'system')")+",\n"+
				"\t"+JSONTools.keyValuePair("paste", "(((ingroup('demonstrator','lecturer')))?'workbook':'system')")+"\n"+
				"}";
	}
}