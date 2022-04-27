package com.example.multidb.examplemultidb.card.service;

import com.example.multidb.examplemultidb.card.model.Card;
import com.example.multidb.examplemultidb.card.repository.CardRepositiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CardService {

    private final Logger log = LoggerFactory.getLogger(CardService.class);

    private final CardRepositiry cardRepositiry;

    public CardService(CardRepositiry cardRepositiry){
        this.cardRepositiry = cardRepositiry;
    }

    public Page<Card> findAll(Pageable pageable){
        log.debug("Enter service to get a Card");
        return cardRepositiry.findAll(pageable);
    }

}
