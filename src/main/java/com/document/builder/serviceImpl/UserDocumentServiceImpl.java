package com.document.builder.serviceImpl;

import com.document.builder.entity.Document;
import com.document.builder.repository.DocumentRepository;
import com.document.builder.service.UserDocumentService;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void downloadCetificate(Map<String, String> requestedVariables, Long documentId) throws FileNotFoundException, Docx4JException, JAXBException {
        Optional<Document> document = documentRepository.findById(documentId);
        if (document.isPresent()) {
            Document dbDocument = document.get();
            try (InputStream fileInputStream = new FileInputStream(dbDocument.getDocumentPath());
                 XWPFDocument docxDocument = new XWPFDocument(fileInputStream)) {
                replaceText(docxDocument,requestedVariables);
               /* for (XWPFParagraph p : docxDocument.getParagraphs()) {
                    requestedVariables.forEach((s, s2) -> {
                        if (p.getParagraphText().contains(s)) {
                            String updatedParagraphText = p.getParagraphText().replace(s,s2);
                            while (p.getRuns().size() > 0) {
                                p.removeRun(0);
                            }
                            XWPFRun newRun = p.createRun();
                            newRun.setText(updatedParagraphText);
                        }
                    });

                }*/
                try (OutputStream outputStream = new FileOutputStream("/home/yogesh/Documents/campusrental/modified_document.docx")) {
                    docxDocument.write(outputStream);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private XWPFDocument replaceText(XWPFDocument doc, Map<String, String> requestedVariables) {
        replaceTextInParagraphs(doc.getParagraphs(), requestedVariables);
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    replaceTextInParagraphs(cell.getParagraphs(),requestedVariables);
                }
            }
        }
        return doc;
    }
    private void replaceTextInParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> requestedVariables) {
        paragraphs.forEach(paragraph -> {
            requestedVariables.forEach((s, s2) -> replaceTextInParagraph(paragraph,s,s2));
            }
        );
    }
    private void replaceTextInParagraph(XWPFParagraph paragraph,String originalText,String updatedText) {
        String paragraphText = paragraph.getParagraphText();
        if (paragraphText.contains(originalText)) {
            String updatedParagraphText = paragraphText.replace(originalText, updatedText);
            while (paragraph.getRuns().size() > 0) {
                paragraph.removeRun(0);
            }
            XWPFRun newRun = paragraph.createRun();
            newRun.setText(updatedParagraphText);
        }
    }

}
