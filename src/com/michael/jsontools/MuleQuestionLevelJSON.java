package com.michael.jsontools;


public class MuleQuestionLevelJSON
{
	private String title;
	private String course;  //Course code e.g. 'CS161'
	private int labNumber; 	//Lab number (String with two digits i.e. leading 0 if <10)
	private int questionNumber; 	//Question number (String with two digits i.e. leading 0 if <10)
	private String[] files; //Filenames of java files e.g. {"BookTester.java", "Book.java", "Magazine.java", "Novel.java"}
	private String qid; 	//E.g. 'CS161_L10Q02'


	//Constructors

	public MuleQuestionLevelJSON(String title, String course, int labNumber, int questionNumber, String[] files)
	{
		this.title = title;
		this.course = course;
		this.labNumber = labNumber;
		this.questionNumber = questionNumber;
		this.files = files;
		setQid();
	}

	public MuleQuestionLevelJSON(String title, String course, int labNumber, int questionNumber, String file)
	{
		this("DEF_TITLE", "DEF_COURSE", 0, 0, new String[] {file});
	}

	public MuleQuestionLevelJSON()
	{
		this("DEF_TITLE", "DEF_COURSE", 0, 0, "DEF_FILENAME.java");
	}



	//Getters

	public String getTitle()
	{
		return title;
	}

	public String getCourse()
	{
		return course;
	}

	public int getLabNumber()
	{
		return labNumber;
	}

	public int getQuestionNumber()
	{
		return questionNumber;
	}

	public String[] getFiles()
	{
		return files;
	}

	public String getQid()
	{
		return qid;
	}


	//Setters

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setCourse(String course)
	{
		this.course = course;
		setQid();
	}

	public void setLabNumber(int labNumber)
	{
		this.labNumber = labNumber;
		setQid();
	}

	public void setQuestionNumber(int questionNumber)
	{
		this.questionNumber = questionNumber;
		setQid();
	}

	public void setFiles(String[] files)
	{
		this.files = files;
	}

	public void setFiles(String file)
	{
		String[] files = {file};
		this.files = files;
	}

	public void setFiles(String file1, String file2)
	{
		String[] files = {file1, file2};
		this.files = files;
	}

	public void setFiles(String file1, String file2, String file3)
	{
		String[] files = {file1, file2, file3};
		this.files = files;
	}

	public void setFiles(String file1, String file2, String file3, String file4)
	{
		String[] files = {file1, file2, file3, file4};
		this.files = files;
	}

	public void setFiles(String file1, String file2, String file3, String file4, String file5)
	{
		String[] files = {file1, file2, file3, file4, file5};
		this.files = files;
	}

	public void setQid(String qid)
	{
		this.qid = qid;
	}

	public void setQid()
	{
		qid = String.format("%s_l%dq%d", numberCharsIn(getCourse()), labNumber, questionNumber);
	}

	public String numberCharsIn(String code) {
		//Question QIDs require the number-component of the course code (i.e. the letters removed) This method returns that
		String out = "";
		for(int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			if(c >= 48 && c <= 57) {
				out += c;
			}
		}
		return out;
	}

	//Tools

	public String toString()
	{
		String filesArray = JSONTools.array(files);

		String out =	"{\n"+
						"\t"+JSONTools.keyValuePair("title", questionNumber+". "+title)+",\n"+
						"\t"+JSONTools.keyValuePairWithoutQuotes("Requested files", filesArray)+",\n"+
						"\t"+JSONTools.keyValuePair("qid", qid)+
						"\n}";

		return out;
	}
}