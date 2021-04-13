package com.pfe.rest;


  	import java.util.Random;

import com.twilio.Twilio;
	import com.twilio.rest.api.v2010.account.Message;
	import com.twilio.type.PhoneNumber;
public class SHA {  

	    public static final String ACCOUNT_SID = "AC0e2a8d720932bfaa5159946156a63163";
	    public static final String AUTH_TOKEN = "aa4f82243153e2c5ef2f43eeb3494939";

	    public static void send(String phone,String code) {
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+216"+phone),
	                new com.twilio.type.PhoneNumber("+12676680593"),
	                "this is your code: "+code)
	            .create();

	        //System.out.println(message.getSid());
	    }
	}
	
	
