package com.document.builder.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserDocumentService {
    void downloadCetificate(Map<String, String> requestedVariables, Long documentId);
}
