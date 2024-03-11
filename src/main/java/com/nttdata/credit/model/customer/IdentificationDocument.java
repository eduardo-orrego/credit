package com.nttdata.credit.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationDocument {

    @Field(value = "type")
    private String type;

    @Field(value = "number")
    private String number;

}
