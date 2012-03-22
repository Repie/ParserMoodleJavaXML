package test;


import dcll.parser.JavatoXMLParser;
import dcll.parser.Xmltojavaparser;
import junit.framework.TestCase;

public class XmlJava extends TestCase {
	
		public void testSensxmljava() {
			Xmltojavaparser x = new Xmltojavaparser();
			x.parse("Documents/quizExamples/quizmoodle.xml");
		}
		
		public void testSensxmljavaxml() {
			Xmltojavaparser x = new Xmltojavaparser();
			JavatoXMLParser y = new JavatoXMLParser();
			y.parse(x.parse("Documents/quizExamples/quizmoodle.xml"),"Documents/quizExamples/quizmoodlebis.xml");

		}

}
