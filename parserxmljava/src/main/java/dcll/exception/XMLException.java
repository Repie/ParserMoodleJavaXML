package dcll.exception;

public class XMLException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XMLException(){
		System.out.println (" XML file is not valid or well-formed");
	}
}
