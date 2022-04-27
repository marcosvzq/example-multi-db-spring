package com.example.multidb.examplemultidb.cardHolder.repository;

import com.example.multidb.examplemultidb.cardHolder.model.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
