package com.nttdata.credit.model.customer;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;

    @Field(value = "type")
    private String type;

    @Field(value = "status")
    private String status;

    @Field(value = "address")
    private Address address;

    @Field(value = "identificationDocuments")
    private IdentificationDocument identificationDocument;

    @Field(value = "personalInfo")
    private PersonalInfo personalInfo;

    @Field(value = "businessInfo")
    private BusinessInfo businessInfo;

    @Field(value = "dateCreated")
    private LocalDateTime dateCreated;

    @Field(value = "lastUpdated")
    private LocalDateTime lastUpdated;


}

