package com.michael.htmltools;

import java.io.*;

public class MuleHTML {

	private String css;
	private String title;
	private String body;
	private String[] notes;
	private String[] imageUrls;
	private String[] sampleCodes;
	String output;
	private String[][] sampleIO; //[rows][cells] i.e. {{input_A, output_A}, {input_B, output_B}...}


	// Constructors

	public MuleHTML(String css, String title, String body, String[] notes, String[] imageUrls, String[] sampleCodes, String output, String[][] sampleIO) {

		this.css = css;
		this.title = title;
		this.body = body;
		this.imageUrls = imageUrls;
		this.notes = notes;
		this.sampleCodes = sampleCodes;
		this.output = output;
		this.sampleIO = sampleIO;
	}

	public MuleHTML() {

		css = "";
		title = "";
		body = "";
		imageUrls = new String[0];
		notes = new String[0];
		sampleCodes = new String[0];
		output = "";
		sampleIO = new String[0][0];
	}


	//Getters

	public String getTitle()
	{
		return title;
	}

	public String getBody()
	{
		return body;
	}

	public String[] getNotes()
	{
		return notes;
	}

	public String getNote(int i)
	{
		return notes[i];
	}

	public String[] getImageUrls()
	{
		return imageUrls;
	}

	public String getImageUrl(int i)
	{
		return imageUrls[i];
	}

	public String[] getSampleCodes()
	{
		return sampleCodes;
	}

	public String getOutput()
	{
		return output;
	}

	public String[][] getSampleIO()
	{
		return sampleIO;
	}


	// Setters

	public void setCSS(String css)
	{
		this.css = css;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public void setNotes(String[] notes)
	{
		this.notes = notes;
	}

	public void setNote(int i, String note)
	{
		notes[i] = note;
	}

	public void setImageUrls(String[] imageUrls)
	{
		this.imageUrls = imageUrls;
	}

	public void setImageUrl(int i, String image)
	{
		imageUrls[i] = image;
	}

	public void setSampleIO(String[][] sampleIO)
	{
		this.sampleIO = sampleIO;
	}

	public void setOutput(String output)
	{
		this.output = output;
	}

	public void setSampleCodes(String[] sampleCodes)
	{
		this.sampleCodes = sampleCodes;
	}

	public void setSampleCode(int i, String code)
	{
		sampleCodes[i] = code;
	}

	public void setSampleIO(int i, String input, String output)
	{
		sampleIO[i][0] = input;
		sampleIO[i][1] = output;
	}

	public void setSampleInput(int i, String input)
	{
		sampleIO[i][0] = input;
	}

	public void setSampleOutput(int i, String output)
	{
		sampleIO[i][1] = output;
	}


	// Tools

	public String addTags(String tag, String content)
	{
		//Returns content enclosed in html tags
		String closingTag = "</"+tag.substring(1);
		return tag+"\n"+content+"\n"+closingTag(tag);
	}

	public String closingTag(String tag)
	{
		String closingTag = "</"+tag.substring(1);
		int indexOfSpace = tag.indexOf(' ');
		if(indexOfSpace != -1)
		{
			closingTag = "</" + tag.substring(1, indexOfSpace) + ">";
		}

		return closingTag;
	}

	public String toString() {
		//Returns a String of the contents of the HTML doc
		String out = "";

		if (css.length() > 0) {
			out += "<head>\n";
			out += addTags("<style>", css) + "\n";
			out += "</head>\n";
			out += "<body>\n";
		}

		if (title.length() > 0)
			out += addTags("<h3>", addTags("<strong>", title)) + "\n<hr />\n";

		if (body.length() > 0)
			out += addTags("<pre class=\"body\">", body) + "\n";

		if (imageUrls.length > 0)
			for (String image : imageUrls)
				out += "<img src=\"" + image + "\" onContextMenu=\"return false;\">\n";

		if (notes.length > 0)
			for (String note : notes)
				out += addTags("<div class=\"note\">", note) + "\n";

		if (sampleCodes.length > 0) {
			String heading = "Sample code";
			for (int i = 0; i < sampleCodes.length; i++) {
				if (sampleCodes.length > 1)
					heading = "Sample code " + (i + 1);
				out += htmlTable("<table class=\"codeBlock sampleCode\">", heading, sampleCodes[i]) + "\n";
			}
		}

		if (output.length() > 0)
		{
			out += htmlTable("<table class=\"sampleIO_table\">", "Output", output) + "\n";
		}
		else if(sampleIO.length > 0)
		{
			String inputHeading = "Sample input";
			String outputHeading = "Sample output";
			if(sampleIO.length > 1)
			{
				inputHeading += "s";
				outputHeading += "s";
			}

			out += htmlTable("<table class=\"sampleIO_table\">", inputHeading, outputHeading, sampleIO)+"\n";
		}

		return out+"</body>\n";
	}

	public String htmlTable(String tag, String h1, String h2, String[][] data)
	{
		String out = tag+"\n"+
					 "<tr>\n"+
					 "<th>\n"+
					 h1+"\n"+
					 "</th>\n"+
					 "<th>\n"+
					 h2+"\n"+
					 "</th>\n"+
					 "</tr>\n";

		for(int r = 0; r < data.length; r++)
		{
			String rowContent = "";

			for(int c = 0; c < data[r].length; c++)
			{
				rowContent += addTags("<td>", "<code>"+data[r][c]+"</code>");
				if(c < data[r].length -1)
					rowContent += "\n";
			}

			out += addTags("<tr>", rowContent)+"\n";
		}

		return out+closingTag(tag);
	}

	public String htmlTable(String tag, String heading, String content)
	{
		return  tag+"\n"+
				"<tr>\n"+
				"<th>\n"+
				heading+"\n"+
				"</th>\n"+
				"</tr>\n"+
				"<tr>\n"+
		   		"<td>\n"+
				"<code>"+content+"</code>\n"+
				"</td>\n"+
				"</tr>\n"+
				closingTag(tag);
	}
}