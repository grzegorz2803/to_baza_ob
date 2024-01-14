package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stolik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numer;

    @OneToMany(mappedBy = "stolik")
    private List<Zamowienie_Stolik> zamowienia;

    public List<Zamowienie_Stolik> getZamowienia() {
        return zamowienia;
    }

    @Override
    public String toString() {
        return "Stolik{" +
                "numer=" + numer +
                ", zamowienia=" + zamowienia +
                '}';
    }
// Gettery, settery, konstruktory, itp.
}
