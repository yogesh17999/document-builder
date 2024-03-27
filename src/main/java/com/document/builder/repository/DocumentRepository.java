package com.document.builder.repository;

import com.document.builder.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends  JpaRepository<Document,Long> {
}
