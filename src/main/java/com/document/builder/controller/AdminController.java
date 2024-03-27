package com.document.builder.controller;

import com.document.builder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/upload/document")
    private String uploadDocument(@RequestPart MultipartFile document, @RequestPart String variable) throws IOException {
            adminService.uploadDocument(document,variable);
        return "success";
    }
}
