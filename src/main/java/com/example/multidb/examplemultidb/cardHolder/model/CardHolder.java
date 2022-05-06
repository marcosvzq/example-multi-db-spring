package com.example.multidb.examplemultidb.cardHolder.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "CARD_HOLDER")
public class CardHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String cardNumber;
}
