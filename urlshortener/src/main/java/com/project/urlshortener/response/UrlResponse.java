package com.project.urlshortener.response;

import java.time.LocalDateTime;

public class UrlResponse {
	
	/* we want to send 3 things
	 * 1. Original url
	 * 2. short url
	 * 3. expiration date
	 */
	private String originalUrl;
	private String shortUrl;
	private LocalDateTime expirationDate;
	
	public UrlResponse(String originalUrl, String shortUrl, LocalDateTime expirationDate) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.expirationDate = expirationDate;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
}
