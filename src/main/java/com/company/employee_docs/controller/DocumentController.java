package com.company.employee_docs.controller;

import com.company.employee_docs.entity.Document;
import com.company.employee_docs.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;


import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // ✅ Step 1: Upload a document to an employee
    @PostMapping("/upload/{employeeId}")
    public ResponseEntity<String> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long employeeId) throws IOException {

        documentService.saveDocument(file, employeeId);
        return ResponseEntity.ok("File uploaded successfully");
    }

    // ✅ Step 2: Download a document by its ID
    @GetMapping("/download/{documentId}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long documentId) {
        Document document = documentService.getDocumentById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found with ID: " + documentId));

        ByteArrayResource resource = new ByteArrayResource(document.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + document.getDocumentName() + "\"")
                .contentType(MediaType.parseMediaType(document.getDocumentType()))
                .body(resource);
    }
        
}
