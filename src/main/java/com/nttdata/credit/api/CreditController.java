package com.nttdata.credit.api;

import com.nttdata.credit.business.CreditService;
import com.nttdata.credit.model.Credit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/credits")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    /**
     * GET /{creditNumber} : Get information about a specific credit
     *
     * @param creditNumber (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "creditsCreditNumberGet",
        summary = "Get information about a specific credit",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Credit.class))
            })
        }
    )
    @GetMapping(
        value = "/{creditNumber}",
        produces = {"application/json"}
    )
    public Mono<Credit> creditsCreditIdGet(
        @Parameter(name = "creditNumber", description = "", required = true, in = ParameterIn.PATH)
        @PathVariable("creditNumber") String creditNumber
    ) {
        return creditService.getCreditByCreditNumber(creditNumber);
    }

    /**
     * GET : Get a list of credits for the customer
     *
     * @param customerId (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "creditsGet",
        summary = "Get a list of credits for the customer",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Credit.class)))
            })
        }
    )
    @GetMapping(
        value = "",
        produces = {"application/json"}
    )
    public Flux<Credit> creditsGet(
        @NotNull @Parameter(name = "customerId", description = "", required = true, in = ParameterIn.QUERY)
        @Validated @RequestParam(value = "customerId") String customerId
    ) {
        return creditService.getCreditsByCustomerId(customerId);
    }

    /**
     * POST : Create a new credit
     *
     * @param credit (optional)
     * @return Created (status code 201)
     */
    @Operation(
        operationId = "creditsPost",
        summary = "Create a new credit",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Credit.class))
            })
        }
    )
    @PostMapping(
        value = "",
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    public Mono<Credit> creditsPost(
        @Parameter(name = "Credit", description = "")
        @Validated @RequestBody(required = false) Credit credit
    ) {
        return creditService.saveCredit(credit);
    }
}