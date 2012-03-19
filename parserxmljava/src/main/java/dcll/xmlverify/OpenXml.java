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


public class OpenXml implements dcll.interfaces.ConstantsTAG{

	private File xmlFile;
	private SAXBuilder sxb;
	private Document document=null;
	
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
	
	public Document checkedQuiz() throws XMLException, XMLRootElementException, XMLNoQuestionsException{
		Document verifiedQuiz=null;
		
		if(this.document==null) throw new XMLException();
		else {
			
			Element root = this.document.getRootElement();
			if(!root.getName().equals(ROOT_ELEMENT)) throw new XMLRootElementException();
			else{
				verifiedQuiz = (Document) this.document.clone();
				List <Element> questions = root.getChildren(); 
				List <Element> intruders =new ArrayList<Element>();
				int nbQuestions = 0;
				for(Element question : questions){
					if(question.getName().equals(QUESTION_ELEMENT)){
						nbQuestions ++;
					}
					else{
						intruders.add(question);
						verifiedQuiz.removeContent(question);
						
					}
					
				}
				
				if(nbQuestions==0) throw new XMLNoQuestionsException();
				
				if(!intruders.isEmpty()){
					createFileIntruders(this.xmlFile.getName(),intruders);									
				}
			} 
				
		}
		
		
		return verifiedQuiz;
	}
	
	
	private void createFileIntruders(String nameFile, List<Element> intruders) {
		// TODO Auto-generated method stub
		String pathFile = System.getProperty("user.dir") + "\\IntrudersXML\\Intruders_"+nameFile.replace(".xml", ".txt");
		try{
			FileWriter fw = new FileWriter(pathFile, true);
			BufferedWriter output = new BufferedWriter(fw);
			
			output.write("All the intruders of file : " + nameFile + "\n");
			
			for(Element intruder : intruders){
				output.write(intruder.toString() +"\n");
				output.write(intruder.getValue() +"\n");
			}
			
			output.flush();
			output.close();
			
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}	
	}

	/*public static void main(String[] args ){
		
		OpenXml op=new OpenXml("Documents\\quizExamples\\malformedQuiz_1.xml");
		op.open();
		try {
			Document tost = op.checkedQuiz();
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLRootElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLNoQuestionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Element test = new Element("quiz");
		String name = op.xmlFile.getName().replace(".xml", ".txt");
		String pathFile = System.getProperty("user.dir") + "\\IntrudersXML\\Intruders_"+name;
		System.out.println(pathFile);
		
		op.createFileIntruders(op.xmlFile.getName(),op.document.getRootElement().getChildren());
		
	} */
	
}
