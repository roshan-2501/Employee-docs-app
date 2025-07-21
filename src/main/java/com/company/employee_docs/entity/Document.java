package com.company.employee_docs.entity;

import com.company.employee_docs.entity.Employee;
import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private String documentType;

    @Lob
    @Column(name = "data", columnDefinition = "BYTEA") // PostgreSQL-specific
    private byte[] data;

    @ManyToOne
    //Tells JPA to create a foreign key column employee_id in the "documents" table referencing the "employees" table.
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Document() {}

    public Document(Long id, String documentName, String documentType, byte[] data, Employee employee) {
        this.id = id;
        this.documentName = documentName;
        this.documentType = documentType;
        this.data = data;
        this.employee = employee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
