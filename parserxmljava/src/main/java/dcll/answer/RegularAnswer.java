package dcll.answer;

import org.jdom.Element;



public class RegularAnswer extends Answer {
	protected int fraction;
	protected String feedback;
	
	public RegularAnswer(String text, int fraction, String feedback) {
		super(text);
		this.fraction = fraction;
		this.feedback = feedback;
	}
	
	public RegularAnswer(String text, String fraction, String feedback) {
		super(text);
		this.fraction = Integer.parseInt(fraction);
		this.feedback = feedback;
	}
	
	public Element parse() {
		Element a = new Element("answer");
		a.setAttribute("fraction", String.valueOf(fraction));
		
		Element e_text = new Element("text").setText(text);
		a.addContent(e_text);
		
		Element e_feedb = new Element("feedback");
		Element e_feed_text = new Element("text").setText(feedback);
		e_feedb.addContent(e_feed_text);
		a.addContent(e_feedb);
		
		return a;
	}
	

}