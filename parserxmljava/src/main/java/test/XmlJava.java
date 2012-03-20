package test;


import dcll.parser.Xmltojavaparser;
import junit.framework.TestCase;

public class XmlJava extends TestCase {
	
		public void test() {
			Xmltojavaparser x = new Xmltojavaparser();
			x.parse("quiz.xml");
		}

}
