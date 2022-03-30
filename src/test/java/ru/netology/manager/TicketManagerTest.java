package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TicketManagerTest {
    private Repository repository = new Repository();
    private TicketManager ticketManager = new TicketManager(repository);
    private Ticket first = new Ticket(1, 5592, "SVO", "KZN", 100);
    private Ticket second = new Ticket(2, 6353, "SVO", "KZN", 105);
    private Ticket third = new Ticket(3, 6361, "SVO", "KZN", 95);
    private Ticket fourth = new Ticket(4, 7263, "SVO", "KZN", 110);
    private Ticket fifth = new Ticket(5, 9448, "SVO", "OVB", 250);
    private Ticket sixth = new Ticket(6, 9448, "SVO", "OVB", 260);
    private Ticket seventh = new Ticket(7, 7094, "SVO", "CEK", 150);
    private Ticket eighth = new Ticket(8, 8105, "SVO", "CEK", 155);
    private Ticket ninth = new Ticket(9, 5889, "SVO", "GRV", 155);
    private Ticket tenth = new Ticket(10, 1899, "SVO", "LED", 85);


    @Test
    public void shouldSortFinalArrayByCostFromCheapToExp() {
        ticketManager.add(first);
        ticketManager.add(second);
        ticketManager.add(third);
        ticketManager.add(seventh);

        Ticket[] actual = ticketManager.find("SVO", "KZN");
        Ticket[] expected = new Ticket[]{first, second, third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteTicket() {
        ticketManager.add(fourth);
        ticketManager.add(fifth);
        ticketManager.add(sixth);
        ticketManager.delete(4);

        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{fifth, sixth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotFindWithoutMatches() {
        ticketManager.add(eighth);
        ticketManager.add(ninth);
        ticketManager.add(tenth);

        Ticket[] actual = ticketManager.find("SVO", "OVB");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(actual, expected);
    }
}