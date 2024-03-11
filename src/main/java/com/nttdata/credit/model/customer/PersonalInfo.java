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
public class PersonalInfo {

    @Field(value = "subType")
    private String subType;

    @Field(value = "fullName")
    private String fullName;

    @Field(value = "nationality")
    private String nationality;

    @Field(value = "birthdate")
    private LocalDate birthdate;

    @Field(value = "email")
    private String email;

    @Field(value = "phoneNumber")
    private String phoneNumber;

}
