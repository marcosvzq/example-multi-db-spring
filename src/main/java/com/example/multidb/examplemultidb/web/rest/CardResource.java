package com.example.multidb.examplemultidb.web.rest;

import com.example.multidb.examplemultidb.card.model.Card;
import com.example.multidb.examplemultidb.card.service.CardService;
import com.example.multidb.examplemultidb.web.rest.error.BadRequestAlertException;
import com.example.multidb.examplemultidb.web.util.HeaderUtil;
import com.example.multidb.examplemultidb.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardResource {

    private final Logger log = LoggerFactory.getLogger(CardResource.class);

    private final CardService cardService;

    public CardResource(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCard(Pageable pageable){
        log.debug("Enter REST to get a page of Card");
        Page<Card> page = cardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "card");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> create(@RequestBody Card card){
        log.debug("Enter REST to create a CARD: {}", card);
        if (card.getId() != null)
            throw new BadRequestAlertException("A new CARD can not have id", Card.ENTITYNAME, "idNotNull");
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert(Card.ENTITYNAME, null))
                .body(cardService.save(card));

    }
}
