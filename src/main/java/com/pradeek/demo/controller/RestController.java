package com.pradeek.demo.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.pradeek.demo.soap.gen.NumberToWords;
import com.pradeek.demo.soap.gen.NumberToWordsResponse;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	
	@Autowired
	private Jaxb2Marshaller marshaller;

	private WebServiceTemplate template;
	
	
	@GetMapping("number/{no}")
	String getnumberTowords(@PathVariable int no) {
		Integer in = no;
		
		BigInteger bi = BigInteger.valueOf(in.intValue());

		NumberToWords numberToWords = new NumberToWords();
		numberToWords.setUbiNum(bi);
		
		template = new WebServiceTemplate(marshaller);
		
		NumberToWordsResponse noNumberToWordsResponse = (NumberToWordsResponse) template.marshalSendAndReceive("https://www.dataaccess.com/webservicesserver/numberconversion.wso?op=NumberToWords",numberToWords);

		return noNumberToWordsResponse.getNumberToWordsResult();
	}
	


}
