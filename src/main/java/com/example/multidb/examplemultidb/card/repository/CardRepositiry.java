package com.example.multidb.examplemultidb.card.repository;

import com.example.multidb.examplemultidb.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepositiry extends JpaRepository<Card, Long> {
}
