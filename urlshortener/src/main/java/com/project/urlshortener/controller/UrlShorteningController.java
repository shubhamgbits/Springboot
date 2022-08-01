package com.project.urlshortener.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.urlshortener.model.Url;
import com.project.urlshortener.model.UrlDto;
import com.project.urlshortener.response.ErrorResponse;
import com.project.urlshortener.response.UrlResponse;
import com.project.urlshortener.service.UrlService;

@RestController
public class UrlShorteningController  {
	
	@Autowired
	private UrlService urlService;
	
	@Autowired
	Gson gson;
	
	@GetMapping("/")
	Object test() throws ParseException {
		return gson.toJson("working");
		
	}
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto){
		
		Url urlToRet = urlService.generateShortLink(urlDto);
		
		if(urlToRet!=null) {
			UrlResponse urlResponse = new UrlResponse(urlToRet.getOriginalUrl(), urlToRet.getShortLink(), urlToRet.getExpirationDate());
			return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
		}
		
		ErrorResponse er = new ErrorResponse("404", "Error processing the request");
		
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.OK);
	}
	
	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response){
		if(StringUtils.isEmpty(shortLink)) {
			ErrorResponse er = new ErrorResponse("404", "Short Link Empty");
			
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.OK);
		}
		
		Url urlToRet = urlService.getEncodedUrl(shortLink);
		
		if(urlToRet == null) {
			ErrorResponse er = new ErrorResponse("404", "Url expired or DNE");
			
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.OK);
		}
		//Checking if url is expired or not
		if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
			ErrorResponse er = new ErrorResponse("200", "Url expired");
			
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.OK);
		}
		 
		try {
			response.sendRedirect(urlToRet.getOriginalUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> generateShortLink(){
		UrlResponse urlResponse = new UrlResponse("testOriginalLink", "testShortLink", LocalDateTime.now());
		return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
	}

}
