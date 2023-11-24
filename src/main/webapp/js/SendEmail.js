/**
 * 
 */
function sendEmail() {
	EmailService es = new EmailService();
	String authKey = es.sendEmail("yrruda94@naver.com");
	System.out.println("Your Email AuthKey : " + authKey);
}