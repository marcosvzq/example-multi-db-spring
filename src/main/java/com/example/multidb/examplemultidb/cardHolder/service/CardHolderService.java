package com.example.multidb.examplemultidb.cardHolder.service;

import com.example.multidb.examplemultidb.cardHolder.model.CardHolder;
import com.example.multidb.examplemultidb.cardHolder.repository.CardHolderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardHolderService {

    private final Logger log = LoggerFactory.getLogger(CardHolderService.class);

    private final CardHolderRepository cardHolderRepository;

    public CardHolderService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }

    public Page<CardHolder> getAllCardHolder(Pageable pageable){
        log.debug("Enter service to getAllCardHolder");
        return cardHolderRepository.findAll(pageable);
    }
}
