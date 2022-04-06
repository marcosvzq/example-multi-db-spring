package com.example.multidb.examplemultidb.card.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CARDS")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return id != null && Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
