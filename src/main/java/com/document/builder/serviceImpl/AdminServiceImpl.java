package com.document.builder.serviceImpl;

import com.document.builder.entity.Document;
import com.document.builder.repository.DocumentRepository;
import com.document.builder.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DocumentRepository documentRepository;
    @Override
    public void uploadDocument(MultipartFile document, String variable) throws IOException {
        String filePath = "/home/yogesh/Documents/";
        File  file =  new File(filePath);
        log.info("file : "+ document.getOriginalFilename() );
        Path path = Paths.get(filePath+File.separator+document.getOriginalFilename());
        Files.write(path,document.getBytes());
       // documentRepository.save(Document.builder().email("email",variable,path,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis())));


    }
}
