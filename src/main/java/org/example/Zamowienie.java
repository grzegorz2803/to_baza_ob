package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataZamowienia;

    @ManyToOne
    private Klient klient;

    @OneToMany(mappedBy = "zamowienie")
    private List<PozycjaZamowienia> pozycjeZamowienia;

    public Zamowienie(Date dataZamowienia, Klient klient) {
        this.dataZamowienia = dataZamowienia;
        this.klient = klient;
    }

    @Override
    public String toString() {
        return "Zamowienie{" +
                "id=" + id +
                ", dataZamowienia=" + dataZamowienia +
                ", klient=" + klient +
                ", pozycjeZamowienia=" + pozycjeZamowienia +
                '}';
    }
    // Gettery, settery, konstruktory, itp.
}
