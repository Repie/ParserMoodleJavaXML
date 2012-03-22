package dcll.answer;

import org.jdom.Element;


/**
 * Answers used by moste questions
 */
public class RegularAnswer extends Answer {
	protected double fraction;
	protected String feedback;
	
	public RegularAnswer(String text, double fraction, String feedback) {
		super(text);
		this.fraction = fraction;
		this.feedback = feedback;
	}
	
	public RegularAnswer(String text, String fraction, String feedback) {
		super(text);
		this.fraction = Integer.parseInt(fraction);
		this.feedback = feedback;
	}
	
	public RegularAnswer(String text, double fraction) {
		super(text);
		this.fraction = fraction;
		this.feedback = new String("");
	}
	
	public RegularAnswer(String text, String fraction) {
		super(text);
		this.fraction = Integer.parseInt(fraction);
		this.feedback = new String("");
	}
	
	public RegularAnswer(Element next) {
		super(next);
		fraction = Double.parseDouble(next.getAttributeValue("fraction"));
		Element eFeedback = next.getChild("feedback");
		if(eFeedback!=null)
			feedback = eFeedback.getChildText("text");
	}

	public double getFraction() {
		return fraction;
	}

	public String getFeedback() {
		return feedback;
	}
	
	
	public Element parse() {
		Element a = new Element("answer");
		a.setAttribute("fraction", String.valueOf(fraction));
		
		Element e_text = new Element("text").setText(text);
		a.addContent(e_text);
		
		if(!feedback.isEmpty()){
			Element e_feedb = new Element("feedback");
			Element e_feed_text = new Element("text").setText(feedback);
			e_feedb.addContent(e_feed_text);
			a.addContent(e_feedb);
		}
		
		return a;
	}

}