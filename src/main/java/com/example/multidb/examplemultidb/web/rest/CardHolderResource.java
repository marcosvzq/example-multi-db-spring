package com.example.multidb.examplemultidb.web.rest;

import com.example.multidb.examplemultidb.cardHolder.model.CardHolder;
import com.example.multidb.examplemultidb.cardHolder.service.CardHolderService;
import com.example.multidb.examplemultidb.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardHolderResource {

    private final Logger log = LoggerFactory.getLogger(CardHolderResource.class);

    private final CardHolderService cardHolderService;

    public CardHolderResource(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    @GetMapping("/cardholders")
    public ResponseEntity<List<CardHolder>> getAllCardHolder(Pageable pageable){
        log.debug("Enter REST to get a page of CardHolder");
        Page<CardHolder> page = cardHolderService.getAllCardHolder(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "CardHolder");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
