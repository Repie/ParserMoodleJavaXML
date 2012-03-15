package dcll.xmlverify;

import java.io.File;
import java.io.IOException;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class OpenXml {

	private File xmlFile;
	private SAXBuilder sxb;
	private org.jdom.Document document=null;
	
	public OpenXml(String xmlFile){
		this.xmlFile=new File(xmlFile);
		this.sxb = new SAXBuilder(); 
	}
	
	public void open(){		
		try{
			this.document=sxb.build(this.xmlFile);
			// If there are no well-formedness or validity errors
			// then no exception is thrown.
			// return true = xml is valid
		}
		// indicates or validity error, but xml is wellformed
		catch (JDOMException e){
			System.out.println( this.xmlFile + " is not valid" );
			System.out.println (e.getMessage());
		}
		catch (IOException e){
			System.out.println("Could not check " + this.xmlFile);
			System.out.println(e.getMessage());
		}
	}
	
	/*public static void main(String[] args ){
		
		OpenXml op=new OpenXml("Documents\\quizExamples\\quizExample_1.xml");
		System.out.println(op.isXmlWellFormed());
		System.out.println(op.document.toString());
	} */
	
}
