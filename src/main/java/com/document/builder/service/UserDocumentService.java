package com.document.builder.service;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Map;

@Service
public interface UserDocumentService {
    void downloadCetificate(Map<String, String> requestedVariables, Long documentId) throws FileNotFoundException, Docx4JException, JAXBException;
}
