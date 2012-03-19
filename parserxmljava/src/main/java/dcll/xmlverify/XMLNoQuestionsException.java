package dcll.xmlverify;

public class XMLNoQuestionsException extends Exception {

	public XMLNoQuestionsException(){
		System.out.println("The quiz doesn't content any questions");
	}
}
