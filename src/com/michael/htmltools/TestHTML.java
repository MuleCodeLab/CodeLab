package com.michael.htmltools;

import java.util.*;
import java.io.*;

public class TestHTML
{
	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		// Entry point to the program
		MuleHTML html = new MuleHTML();
		File doc = new File("description.html");

		FileWriter fw = new FileWriter(doc);
		PrintWriter pw = new PrintWriter(fw);

		File cssFile = new File("style.css");
		html.setCSS(fileToString(cssFile));

		html.setTitle("Hello World!");
		html.setBody("Ut tristique libero massa, et vulputate risus ullamcorper aliquet. In venenatis interdum tincidunt. Quisque congue mauris a sem ultricies posuere. Nam risus magna, feugiat vel pretium ornare, faucibus eget nunc. Etiam lobortis hendrerit lacus. Proin bibendum tellus nibh, eget lobortis ex rhoncus vel. Praesent tincidunt quis massa vitae bibendum. Ut nec purus ex. Fusce bibendum eu libero vel auctor.");

		String[] images = new String[1];
		images[0] = "https://i.imgur.com/0INnK3x.png";
		html.setImageUrls(images);

		String[] notes = new String[1];
		notes[0] = "You must give your method the same name as in the second sample code below.";
		html.setNotes(notes);

		html.setOutput(
			"Some lab questions expect a single exact output, for example where the solution program takes no input.\n"+
			"This is where that output can be displayed, without the need for a sample input/output table"
		);

		String[] sampleCodes = new String[2];
		sampleCodes[0] = "public static void main(String[] args)\n"+
						 "{\n"+
						 "\tSystem.out.println(\"Hello World!\");\n"+
						 "}";

		sampleCodes[1] = "import java.util.Scanner;\n\n"+
						 "public static void main(String[] args)\n"+
						 "{\n\t"+
						 "printHelloWorld();"+
						 "\n}\n\n"+
						 "public static void printHelloWorld()\n"+
						 "{\n\t"+
						 "System.out.println(\"Hello World!\");\n"+
						 "}";

		html.setSampleCodes(sampleCodes);

		String[][] sampleIO = new String[2][2];
		for(int i = 0; i < sampleIO.length; i++)
		{
			sampleIO[i][0] = "input"+(i+1)+"\nsecond line";
			sampleIO[i][1] = "output"+(i+1);
		}
		html.setSampleIO(sampleIO);

		pw.write(html.toString());
		pw.close();
	}



	// Methods

	public static String fileToString(File file)
	{
		try
		{
			Scanner read = new Scanner(file);
			String out = "";

			while(read.hasNextLine())
			{
				String line = read.nextLine();
				out += line;

				if(read.hasNextLine())
					out += "\n";
			}
			read.close();

			return out;
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		return null;
	}
}