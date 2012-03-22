package dcll.exception;

public class XMLRootElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XMLRootElementException(){
		System.out.println("Root element in XML file is not available");
	}
}
