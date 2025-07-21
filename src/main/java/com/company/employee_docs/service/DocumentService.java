package com.company.employee_docs.service;

import com.company.employee_docs.entity.Document;
import com.company.employee_docs.entity.Employee;
import com.company.employee_docs.repository.DocumentRepository;
import com.company.employee_docs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save uploaded document with file content
    public void saveDocument(MultipartFile file, Long employeeId) throws IOException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }

        Document document = new Document();
        document.setDocumentName(file.getOriginalFilename());
        document.setDocumentType(file.getContentType());
        document.setData(file.getBytes());
        document.setEmployee(optionalEmployee.get());

        documentRepository.save(document);
    }

    // Get all documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Get document by ID
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }



    // Delete document
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }

    // Load document as Resource for download
    public Resource loadDocumentAsResource(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with ID: " + id));
        return new ByteArrayResource(document.getData());
    }
}
