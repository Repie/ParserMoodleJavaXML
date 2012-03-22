package test;


import dcll.parser.JavatoXMLParser;
import dcll.parser.Xmltojavaparser;
import junit.framework.TestCase;

public class XmlJava extends TestCase {
	
		public void testSensxmljava() {
			Xmltojavaparser x = new Xmltojavaparser();
			x.parse("C:\\Users\\Julien\\Documents\\Cours\\DCLL\\Projet\\quiz.xml");
		}
		
		public void testSensxmljavaxml() {
			Xmltojavaparser x = new Xmltojavaparser();
			JavatoXMLParser y = new JavatoXMLParser();
			y.parse(x.parse("C:\\Users\\Julien\\Documents\\Cours\\DCLL\\Projet\\quizmoodle.xml"),"C:\\Users\\Julien\\Documents\\Cours\\DCLL\\Projet\\quizmoodlebis.xml");

		}

}
