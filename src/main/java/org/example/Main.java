package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Potrawa").executeUpdate();
        em.createQuery("DELETE FROM Zamowienie_Stolik").executeUpdate();
        em.createQuery("DELETE FROM Rachunek").executeUpdate();
        em.createQuery("DELETE FROM Pracownik").executeUpdate();
        em.createQuery("DELETE FROM Klient").executeUpdate();
        em.createQuery("DELETE FROM Stolik").executeUpdate();
        em.createQuery("DELETE FROM PozycjaZamowienia").executeUpdate();
        em.createQuery("DELETE FROM Zamowienie").executeUpdate();
        Stolik stolik3 = new Stolik();
        em.persist(stolik3);

        Stolik stolik4 = new Stolik();
        em.persist(stolik4);
        // Wstawianie danych do bazy
        Klient klient1 = new Klient("Jan", "Kowalski");
        em.persist(klient1);
        Klient klient2 = new Klient("Jan", "Nowak");
        em.persist(klient2);
        Pracownik pracownik1 = new Pracownik("Jan", "Nowak");
        em.persist(pracownik1);

        Pracownik pracownik2 = new Pracownik("Anna", "Kowalska");
        em.persist(pracownik2);
        Potrawa salatkaCezar = new Potrawa("Sałatka Cezar", 25.99, "Sałatki");
        em.persist(salatkaCezar);

        Zamowienie zamowienie1 = new Zamowienie(new Date(), klient1);
        em.persist(zamowienie1);
        Zamowienie zamowienie2 = new Zamowienie(new Date(), klient2);
        em.persist(zamowienie2);
        // Dodaj rachunki dla zamówień
        Rachunek rachunek1 = new Rachunek();
        em.persist(rachunek1);

        Rachunek rachunek2 = new Rachunek();
        em.persist(rachunek2);



        PozycjaZamowienia pozycja1 = new PozycjaZamowienia(zamowienie1, salatkaCezar, 2);
        em.persist(pozycja1);
        // 12. INSERT INTO Potrawa { Nazwa: 'Schabowy', Cena: 35.99, Kategoria: 'Dania główne' };
        Potrawa schabowy = new Potrawa("Schabowy", 35.99, "Dania główne");
        em.persist(schabowy);

        Potrawa kaczka = new Potrawa("Kaczka pieczona", 45.99, "Dania główne");
        em.persist(kaczka);

        Potrawa pierogi = new Potrawa("Pierogi ruskie", 20.99, "Dania główne");
        em.persist(pierogi);

        Query updateQuery = em.createQuery("UPDATE Potrawa SET cena = :cena WHERE nazwa = :nazwa");
        updateQuery.setParameter("cena", 28.99);
        updateQuery.setParameter("nazwa", "Penne Carbonara");
        updateQuery.executeUpdate();

        // 3. DELETE FROM Zamowienie WHERE ID = 234
        Query deleteQuery = em.createQuery("DELETE FROM Zamowienie WHERE id = :id");
        deleteQuery.setParameter("id", 234);
        deleteQuery.executeUpdate();

        // 4. SELECT MIN(Potrawa.Cena) FROM Potrawa
        Query minPriceQuery = em.createQuery("SELECT MIN(p.cena) FROM Potrawa p");
        Double minPrice = (Double) minPriceQuery.getSingleResult();
        System.out.println("Najniższa cena potrawy: " + minPrice);

        // 5. SELECT Potrawa FROM Potrawa
        TypedQuery<Potrawa> allDishesQuery = em.createQuery("SELECT p FROM Potrawa p", Potrawa.class);
        List<Potrawa> allDishes = allDishesQuery.getResultList();
        System.out.println("Wszystkie potrawy: " + allDishes);



        // 7. SELECT COUNT(Pracownik) FROM Pracownik;
        Query employeesCountQuery = em.createQuery("SELECT COUNT(p) FROM Pracownik p");
        Long employeesCount = (Long) employeesCountQuery.getSingleResult();
        System.out.println("Liczba pracowników: " + employeesCount);

        // 8. SELECT COUNT(Zamowienie) FROM Zamowienie WHERE Zamowienie.Klient.ID = 123;
        Query ordersCountForClientQuery = em.createQuery("SELECT COUNT(z) FROM Zamowienie z WHERE z.klient.id = 123");
        Long ordersCountForClient = (Long) ordersCountForClientQuery.getSingleResult();
        System.out.println("Liczba zamówień dla klienta o ID 123: " + ordersCountForClient);


        // 10. SELECT AVG(Potrawa.Cena) AS SredniaCena FROM Potrawa WHERE Potrawa.Kategoria = 'Dania główne';
        Query avgPriceForMainDishesQuery = em.createQuery("SELECT AVG(p.cena) FROM Potrawa p WHERE p.kategoria = 'Dania główne'");
        Double avgPriceForMainDishes = (Double) avgPriceForMainDishesQuery.getSingleResult();
        System.out.println("Średnia cena dań głównych: " + avgPriceForMainDishes);

//         salatkaCezar = new Potrawa();
//        salatkaCezar.setNazwa("Sałatka Cezar");
//        salatkaCezar.setCena(25.99);
//        salatkaCezar.setKategoria("Sałatki");
//        em.persist(salatkaCezar);

        // 12. SELECT Zamowienie FROM Zamowienie WHERE Zamowienie.Klient.ID = 780 AND Zamowienie.DataZamowienia > NOW() - 3 MONTHS;
        TypedQuery<Zamowienie> recentOrdersQuery = em.createQuery("SELECT z FROM Zamowienie z WHERE z.klient.id = 780 AND z.dataZamowienia > :threeMonthsAgo", Zamowienie.class);
        recentOrdersQuery.setParameter("threeMonthsAgo", new Date(System.currentTimeMillis() - 3 * 30 * 24 * 60 * 60 * 1000));
        List<Zamowienie> recentOrders = recentOrdersQuery.getResultList();
        System.out.println("Zamówienia dla klienta o ID 780 z ostatnich 3 miesięcy: " + recentOrders);

            // Pobierz wszystkie potrawy
             allDishesQuery = em.createQuery("SELECT p FROM Potrawa p", Potrawa.class);
          allDishes = allDishesQuery.getResultList();
            System.out.println("Wszystkie potrawy: " + allDishes);

            // Pobierz wszystkie zamówienia
            TypedQuery<Zamowienie> allOrdersQuery = em.createQuery("SELECT z FROM Zamowienie z", Zamowienie.class);
            List<Zamowienie> allOrders = allOrdersQuery.getResultList();
            System.out.println("Wszystkie zamówienia: " + allOrders);

            // Pobierz wszystkie rachunki
            TypedQuery<Rachunek> allBillsQuery = em.createQuery("SELECT r FROM Rachunek r", Rachunek.class);
            List<Rachunek> allBills = allBillsQuery.getResultList();
            System.out.println("Wszystkie rachunki: " + allBills);

            // Pobierz wszystkich pracowników
            TypedQuery<Pracownik> allEmployeesQuery = em.createQuery("SELECT p FROM Pracownik p", Pracownik.class);
            List<Pracownik> allEmployees = allEmployeesQuery.getResultList();
            System.out.println("Wszyscy pracownicy: " + allEmployees);

            // Pobierz wszystkich klientów
            TypedQuery<Klient> allCustomersQuery = em.createQuery("SELECT k FROM Klient k", Klient.class);
            List<Klient> allCustomers = allCustomersQuery.getResultList();
            System.out.println("Wszyscy klienci: " + allCustomers);

            // Pobierz wszystkie stoliki
            TypedQuery<Stolik> allTablesQuery = em.createQuery("SELECT s FROM Stolik s", Stolik.class);
            List<Stolik> allTables = allTablesQuery.getResultList();
            System.out.println("Wszystkie stoliki: " + allTables);

            // Pobierz wszystkie pozycje zamówienia
            TypedQuery<PozycjaZamowienia> allOrderItemsQuery = em.createQuery("SELECT p FROM PozycjaZamowienia p", PozycjaZamowienia.class);
            List<PozycjaZamowienia> allOrderItems = allOrderItemsQuery.getResultList();
            System.out.println("Wszystkie pozycje zamówienia: " + allOrderItems);
        em.close();
        emf.close();
    }
}