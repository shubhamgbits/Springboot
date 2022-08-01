package com.project.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.urlshortener.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

	public Url findByShortLink(String ShortLink);
}
