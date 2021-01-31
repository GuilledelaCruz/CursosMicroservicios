package es.guilledelacruz.restfulwebservices.beans;

public class HelloWorldBean {

	private String message = "HelloWorld!";

	public HelloWorldBean() {}
	
	public HelloWorldBean(String value) {
		message += " Variable: " + value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
