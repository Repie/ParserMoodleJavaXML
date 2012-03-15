package dcll.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.Answer;
import question.Description;

public class Description extends TestCase {
	ArrayList<Answer> a = new ArrayList<Answer>();
	
	Description q = new Description("Consigne dispositif électronique", a, "Pas de calculatrice !");

}
