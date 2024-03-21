package com.nttdata.credit.api.request;

import com.nttdata.credit.enums.CreditTypeEnum;
import com.nttdata.credit.enums.CurrencyTypeEnum;
import com.nttdata.credit.enums.StatusTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: CreditRequest. <br/>
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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {

  @Size(min = 16, max = 16,message = "El campo 'accountNumber' debe tener 16 digitos")
  private BigInteger creditNumber;

  @NotNull(message = "El campo 'type' no puede ser nulo")
  private CreditTypeEnum type;

  @NotNull(message = "El campo 'status' no puede ser nulo")
  private StatusTypeEnum status;

  @NotNull(message = "El campo 'currency' no puede ser nulo")
  private CurrencyTypeEnum currency;

  @NotNull(message = "El campo 'customerDocument' no puede ser vacío")
  private BigInteger customerDocument;

  @NotNull(message = "El campo 'amount' no puede ser nulo")
  private BigDecimal amount;

  private LocalDate disbursementDate;
  private LocalDate dueDate;
  private BigDecimal outstandingBalance;
  private BigDecimal interestRate;
  private BigDecimal availableBalance;
  private BigDecimal creditLimit;

}
