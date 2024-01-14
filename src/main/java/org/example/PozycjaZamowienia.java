package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PozycjaZamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Zamowienie zamowienie;

    @ManyToOne
    private Potrawa potrawa;

    private int ilosc;

    public PozycjaZamowienia(Zamowienie zamowienie, Potrawa potrawa, int ilosc) {
        this.zamowienie = zamowienie;
        this.potrawa = potrawa;
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "PozycjaZamowienia{" +
                "id=" + id +
                ", zamowienie=" + zamowienie +
                ", potrawa=" + potrawa +
                ", ilosc=" + ilosc +
                '}';
    }
    // Gettery, settery, konstruktory, itp.
}
