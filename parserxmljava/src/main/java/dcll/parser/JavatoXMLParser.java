package dcll.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;

import dcll.interfaces.Javatoxml;
import dcll.quiz.Quiz;
import dcll.quiz.QuizWriter;

public class JavatoXMLParser implements Javatoxml{

	public void parse(Quiz q, String filename) {
		try {
			QuizWriter.write(new Document(q.parse()), new FileWriter(new File(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
