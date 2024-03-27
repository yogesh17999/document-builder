package com.document.builder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface AdminService {
    void uploadDocument(MultipartFile document, String variable) throws IOException;

}
