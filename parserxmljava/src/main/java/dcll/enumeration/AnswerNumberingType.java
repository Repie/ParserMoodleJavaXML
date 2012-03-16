package dcll.enumeration;


public enum AnswerNumberingType {
	NONE,
	ABC,
	ABCD,
	A123;
	
	public String toString(){
		if(this.equals(A123))
			return "123";
		else if (this.equals(ABCD))
			return "ABCD";
		else if (this.equals(ABC))
			return "abc";
		else
			return "123";
			
	}
}
