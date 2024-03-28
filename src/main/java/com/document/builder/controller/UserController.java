package com.document.builder.controller;

import com.document.builder.service.UserDocumentService;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Map;

@RequestMapping("/user/api")
@RestController
public class UserController {

    @Autowired
    private UserDocumentService userDocumentService;

    @PostMapping("/v1/download/document")
    public String downloadDocument(@RequestBody Map<String,String> requestedVariables,@RequestParam Long documentId) throws JAXBException, FileNotFoundException, Docx4JException {
        userDocumentService.downloadCetificate(requestedVariables,documentId);
        return "success";
    }

}
