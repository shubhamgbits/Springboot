package com.project.urlshortener.service;

import org.springframework.stereotype.Service;

import com.project.urlshortener.model.Url;
import com.project.urlshortener.model.UrlDto;

@Service
public interface UrlService {

	public Url generateShortLink(UrlDto urlDto);
	public Url persistShortLink(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url url);
}
