package dcll.xmlverify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Class open a XMLFile
 * Check validity and wellform of XML file
 * Check consistent quiz
 * @author Pierre Rigaud
 *
 */
public class OpenXml implements dcll.interfaces.ConstantsTAG{

	private File xmlFile;
	private SAXBuilder sxb;
	private Document document=null;
	
	/**
	 * Constructor OpenXml
	 * @author Pierre Rigaud
	 * @param xmlFile as String like "Documents\\quizExamples\\malformedQuiz_1.xml"
	 */
	public OpenXml(String xmlFile){
		this.xmlFile=new File(xmlFile);
		this.sxb = new SAXBuilder(); 
	}
	
	/**
	 * open an XML file, indicates a validy error or a no well-formedness error
	 * if all is ok, set the OpenXML param document
	 * @author Pierre Rigaud
	 * 
	 */
	public void open(){		
		try{
			this.document=sxb.build(this.xmlFile);
			// If there are no well-formedness or validity errors
			// then no exception is thrown.
			// return true = xml is valid
		}
		// indicates a validity error or a no well-formedness error
		catch (JDOMException e){
			System.out.println( this.xmlFile + " is not valid" );
			System.out.println (e.getMessage());
		}
		catch (IOException e){
			System.out.println("Could not check " + this.xmlFile);
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Check a quiz and indicates if is not a consistent quiz
	 * create a file with intruders
	 * @author Pierre Rigaud
	 * @throws XMLException if XML file is not valid or badformed
	 * @throws XMLRootElementException if the root Element is not quiz
	 * @throws XMLNoQuestionsException if Element quiz contains another tags that question tag
	 */
	public void checkedQuiz() throws XMLException, XMLRootElementException, XMLNoQuestionsException{
		
		// check is a XMLfile is badlformed
		if(this.document==null) throw new XMLException();
		// if the XMLfile is ok
		else {
			
			// get the root element of XMLfile
			Element root = this.document.getRootElement();
			// check if the root element is different to ROOT_ELEMENT quiz
			if(!root.getName().equals(ROOT_ELEMENT)) throw new XMLRootElementException();
			// if the root element is ok
			else{
				
				@SuppressWarnings("unchecked")
				// get children of the root element
				List <Element> questions = root.getChildren(); 
				// create list ,it will retrieve the intrues tags
				List <Element> intruders =new ArrayList<Element>();
				// set the number questions check at 0
				int nbQuestions = 0;
				// for each child tags
				for(Element question : questions){
					// check if tag is QUESTION_ELEMENT
					if(question.getName().equals(QUESTION_ELEMENT)){
						// if tag is ok, increment the number questions check
						nbQuestions ++;
					}
					else{
						// else add the intruder in the intruders list
						intruders.add(question);
						
					}
					
				}
				
				// if no question find in quiz, indicates that thanks to Exception
				if(nbQuestions==0) throw new XMLNoQuestionsException();
				
				// if intruders are found
				if(!intruders.isEmpty()){
					// write intruders in other file
					createFileIntruders(this.xmlFile.getName(),intruders);									
				}
			} 
				
		}

	}
	
	/**
	 * @author Pierre Rigaud
	 * @param nameFile of XMLfile
	 * @param intruders list of intrude tags
	 */
	private void createFileIntruders(String nameFile, List<Element> intruders) {
		// TODO Auto-generated method stub
		// set the pathFile of Intruders File
		String pathFile = System.getProperty("user.dir") + "\\Documents\\IntrudersXML\\Intruders_"+nameFile.replace(".xml", ".txt");
		try{
			// create the FileWriter and BufferedWriter to write intruders in a file
			FileWriter fw = new FileWriter(pathFile, true);
			BufferedWriter output = new BufferedWriter(fw);
			
			// header of file with the name of XmlFile
			output.write("All the intruders of file : " + nameFile + "\n");
			
			// for each intruder
			for(Element intruder : intruders){
				// write his tag
				output.write(intruder.toString() +"\n");
				// write his value
				output.write(intruder.getValue() +"\n");
			}
			
			// file close
			output.flush();
			output.close();
			
		}
		// catch af File error
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}	
	}
	
}
