package dcll.interfaces;

import dcll.quiz.Quiz;

public interface Xmltojava {

	/**
	 * This function return the Quiz object matching with the xml file in param.
	 * filename is the patch to the file
	 * 
	 * @param filename
	 * @return Quiz object
	 */
	Quiz parse(String filename);

}
