package dcll.answer;

import org.jdom.Element;

/**
 * Answers used by Numerical questions
 */
public class NumericalAnswer extends RegularAnswer {
	protected double tolerance = 0;

	public NumericalAnswer(String text, double fraction, String feedback) {
		super(text, fraction, feedback);
	}

	public NumericalAnswer(String text, double fraction) {
		super(text, fraction);
	}

	public NumericalAnswer(String text, String fraction, String feedback) {
		super(text, fraction, feedback);
	}

	public NumericalAnswer(String text, String fraction) {
		super(text, fraction);
	}
	
	public NumericalAnswer(String text, double fraction, String feedback, double tolerance) {
		super(text, fraction, feedback);
		this.tolerance = tolerance;
	}

	public NumericalAnswer(String text, double fraction, double tolerance) {
		super(text, fraction);
		this.tolerance = tolerance;
	}

	public NumericalAnswer(String text, String fraction, String feedback, double tolerance) {
		super(text, fraction, feedback);
		this.tolerance = tolerance;
	}

	public NumericalAnswer(String text, String fraction, double tolerance) {
		super(text, fraction);
		this.tolerance = tolerance;
	}
	
	public NumericalAnswer(Element next) {
		super(next);
		
		Element eTolerance = next.getChild("tolerance");
		if(eTolerance!=null)
			tolerance = Double.parseDouble(eTolerance.getText());
		
	}

	public Element parse() {
		Element a = new Element("answer");
		a.setAttribute("fraction", String.valueOf(fraction));
		
		Element e_text = new Element("text").setText(text);
		a.addContent(e_text);
		
		Element e_tol = new Element("tolerance").setText(String.valueOf(tolerance));
		a.addContent(e_tol);
		
		if(!feedback.isEmpty()){
			Element e_feedb = new Element("feedback");
			Element e_feed_text = new Element("text").setText(feedback);
			e_feedb.addContent(e_feed_text);
			a.addContent(e_feedb);
		}
		
		return a;
	}

}
