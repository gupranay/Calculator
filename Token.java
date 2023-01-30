package com.vv.programming.vvcalc;

public class Token {

	private String value;
	private TokenType type;
	private Operator operator;

	public String getValue() {
		if(TokenType.OPERATOR.equals(type)){
			return ""+operator.getValue();
		} else {
			return value;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	 public Operator getOperator() {
	 return operator;
	 }
	
	 public void setOperator(Operator operator) {
	 this.operator = operator;
	 }
	
	@Override
	public String toString() {
		if(TokenType.OPERATOR.equals(type)) {
			return "Token [operator=" + operator.getValue() + "]";			
		} else {
			return "Token [value=" + value + "]";
		}
	}

	

}
