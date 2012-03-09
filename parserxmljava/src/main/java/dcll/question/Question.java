package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.interfaces.Parser;



public class Question implements Parser{
	protected QuestionType type;
	public String name;
	public QuestionTextFormat format;
	public String text;
	public ArrayList<Answer> answers;
}
