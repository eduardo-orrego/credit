package com.nttdata.credit.model.customer;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo {

    @Field(value = "subType")
    private String subType;

    @Field(value = "legalName")
    private String legalName;

    @Field(value = "tradeName")
    private String tradeName;

    @Field(value = "incorporationDate")
    private LocalDate incorporationDate;

    @Field(value = "website")
    private String website;

    @Field(value = "fax")
    private String fax;

}
