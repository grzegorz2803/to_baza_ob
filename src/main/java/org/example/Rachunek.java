package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rachunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Zamowienie zamowienie;

    private double kwota;

    @Override
    public String toString() {
        return "Rachunek{" +
                "id=" + id +
                ", zamowienie=" + zamowienie +
                ", kwota=" + kwota +
                '}';
    }
// Gettery, settery, konstruktory, itp.
}
