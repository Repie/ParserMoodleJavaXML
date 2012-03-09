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
	
	
	

}