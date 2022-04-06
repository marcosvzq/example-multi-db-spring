package com.example.multidb.examplemultidb.cardHolder.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CARD_HOLDER")
public class CardHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String memberId;
    private String cardNumber;
}
