package com.document.builder.controller;

import com.document.builder.service.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/user/api")
@RestController
public class UserController {

    @Autowired
    private UserDocumentService userDocumentService;

    @PostMapping("/v1/download/document")
    public String downloadDocument(@RequestBody Map<String,String> requestedVariables,@RequestParam Long documentId){
        userDocumentService.downloadCetificate(requestedVariables,documentId);
        return "success";
    }

}
