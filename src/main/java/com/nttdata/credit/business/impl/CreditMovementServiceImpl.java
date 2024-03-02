package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CreditMovementService;
import com.nttdata.credit.model.credit.CreditMovement;
import com.nttdata.credit.repository.CreditMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditMovementServiceImpl implements CreditMovementService {

    private final CreditMovementRepository creditMovementRepository;

    @Autowired
    public CreditMovementServiceImpl(CreditMovementRepository creditMovementRepository) {
        this.creditMovementRepository = creditMovementRepository;
    }

    @Override
    public Mono<CreditMovement> saveMovementCredit(CreditMovement creditMovement) {
        return creditMovementRepository.save(creditMovement);
    }

    @Override
    public Flux<CreditMovement> getMovementsCredit(String creditId) {
        return creditMovementRepository.findByCreditNumber(creditId);
    }
}
