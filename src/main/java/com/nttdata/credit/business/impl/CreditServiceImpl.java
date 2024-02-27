package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CreditService;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public Mono<Credit> getCreditByCreditNumber(String creditNumber) {
        return creditRepository.findByCreditNumber(creditNumber);
    }

    @Override
    public Flux<Credit> getCreditsByCustomerId(String customerId) {
        return creditRepository.findByHolderId(customerId);
    }

    @Override
    public Mono<Credit> saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

}
