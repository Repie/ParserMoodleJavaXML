package dcll.interfaces;

import dcll.quiz.Quiz;

public interface APIParserMoodleJavaXML {
	
	Quiz parse (String filename);
	
	void parse(Quiz q, String filname);


}
