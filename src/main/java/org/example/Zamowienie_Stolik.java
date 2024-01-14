package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zamowienie_Stolik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zamowienie zamowienie;

    @ManyToOne
    private Stolik stolik;

    @Override
    public String toString() {
        return "Zamowienie_Stolik{" +
                "id=" + id +
                ", zamowienie=" + zamowienie +
                ", stolik=" + stolik +
                '}';
    }

    public Zamowienie_Stolik(Zamowienie zamowienie, Stolik stolik) {
        this.zamowienie = zamowienie;
        this.stolik = stolik;
    }
// Gettery, settery, konstruktory, itp.
}
