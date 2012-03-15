package dcll.test;

import java.util.ArrayList;

import dcll.answer.Answer;
import junit.framework.TestCase;

public class Essay extends TestCase {
	ArrayList<Answer> a = new ArrayList<Answer>();
	a.add(new RegularAnswer(new String(""), 0, new String("")));
	
	Essay q = new Essay("Question ouverte", a, "Ecrire un programme qui affiche Hello world");
}
