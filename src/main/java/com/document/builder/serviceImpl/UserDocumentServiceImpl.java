package com.document.builder.serviceImpl;

import com.document.builder.entity.Document;
import com.document.builder.repository.DocumentRepository;
import com.document.builder.service.UserDocumentService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void downloadCetificate(Map<String, String> requestedVariables, Long documentId) {
        Optional<Document> document = documentRepository.findById(documentId);
        if(document.isPresent()){
            Document dbDocument= document.get();
            try(InputStream fileInputStream = new FileInputStream(dbDocument.getDocumentPath());
                POIFSFileSystem fileSystem = new POIFSFileSystem(fileInputStream)) {
                HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
                requestedVariables.forEach((s, s2) -> {
                    hwpfDocument.getRange().replaceText(s,s2);
                });
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
