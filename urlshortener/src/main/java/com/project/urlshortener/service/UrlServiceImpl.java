package com.project.urlshortener.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.project.urlshortener.model.Url;
import com.project.urlshortener.model.UrlDto;
import com.project.urlshortener.repository.UrlRepository;

@Component
public class UrlServiceImpl implements UrlService {

	@Autowired
	private UrlRepository urlRepository;
	
	@Override
	public Url generateShortLink(UrlDto urlDto) {
		if(StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
			
			//Now we have to persist our short link into the DB
			Url urlToPersist = new Url();
			
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
			
			Url urlToReturn = persistShortLink(urlToPersist);
			
			if(urlToReturn != null)
				return urlToReturn;
			
			return null;
			
		}
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if(StringUtils.isBlank(expirationDate))
			return creationDate.plusMinutes(5);
		
		return LocalDateTime.parse(expirationDate);
	}

	private String encodeUrl(String url) {
		// Here we will make use of guava library to generate short link
		
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		
		encodedUrl = Hashing.murmur3_128()
				.hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
				.toString();
		
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		
		Url urlToRet = urlRepository.save(url);
		
		return urlToRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		
		Url urlToRet  = urlRepository.findByShortLink(url);
		
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {
		urlRepository.delete(url);	
	}

}
