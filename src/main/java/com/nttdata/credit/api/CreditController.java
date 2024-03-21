package com.nttdata.credit.api;

import com.nttdata.credit.api.request.CreditRequest;
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
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditController. <br/>
 * <b>Bootcamp NTTDATA</b><br/>
 *
 * @author NTTDATA
 * @version 1.0
 *   <u>Developed by</u>:
 *   <ul>
 *   <li>Developer Carlos</li>
 *   </ul>
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/api/credits")
public class CreditController {

  @Autowired
  private CreditService creditService;

  /**
   * POST : Create a new credit.
   *
   * @param credit (required)
   * @return Created (status code 201)
   */
  @Operation(
    operationId = "creditPost",
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
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Credit> creditPost(
    @Parameter(name = "credit", description = "")
    @Validated @RequestBody CreditRequest credit
  ) {
    return creditService.saveCredit(credit);
  }

  /**
   * PUT : Update a credit exists.
   *
   * @param credit   (required)
   * @param creditId (required)
   * @return Created (status code 200)
   */
  @Operation(
    operationId = "creditPut",
    summary = "Update a credit exists",
    responses = {
      @ApiResponse(responseCode = "200", description = "Ok", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Credit.class))
      })
    }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(
    value = "/{creditId}",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  public Mono<Credit> creditPut(
    @Parameter(name = "creditId", description = "", required = true, in = ParameterIn.PATH)
    @PathVariable("creditId") String creditId,
    @Parameter(name = "credit", description = "")
    @Validated @RequestBody CreditRequest credit
  ) {
    return creditService.updateCredit(credit, creditId);
  }

  /**
   * GET /{creditNumber} : Get information about a specific credit.
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
  @ResponseStatus(HttpStatus.OK)
  public Mono<Credit> creditsCreditIdGet(
    @Parameter(name = "creditNumber", description = "", required = true, in = ParameterIn.PATH)
    @PathVariable("creditNumber") BigInteger creditNumber
  ) {
    return creditService.getCreditByCreditNumber(creditNumber);
  }

  /**
   * GET : Get a list of credits for the customer.
   *
   * @param customerDocument (required)
   * @return OK (status code 200)
   */
  @Operation(
    operationId = "creditsGet",
    summary = "Get a list of credits for the customer",
    responses = {
      @ApiResponse(responseCode = "200", description = "OK", content = {
        @Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = Credit.class)))
      })
    }
  )
  @GetMapping(
    value = "",
    produces = {"application/json"}
  )
  @ResponseStatus(HttpStatus.OK)
  public Flux<Credit> creditsGet(
    @NotNull @Parameter(name = "customerDocument", description = "", required = true,
      in = ParameterIn.QUERY)
    @Validated @RequestParam(value = "customerDocument") BigInteger customerDocument
  ) {
    return creditService.getCreditsByCustomerDocument(customerDocument);
  }

}