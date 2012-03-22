package dcll.interfaces;

import org.jdom.Element;

/**
 * Interface implemented by any parsable object (Quiz, Question and Answer)
 */
public interface Parsable {
	/**
	 * Returns the corresponding JDom Element
	 */
	Element parse();
}
