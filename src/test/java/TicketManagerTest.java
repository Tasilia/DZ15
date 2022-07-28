import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    Ticket ticket1 = new Ticket(1, 1000, "LED", "GOJ", 200);
    Ticket ticket2 = new Ticket(2, 1001, "GOJ", "LED", 202);
    Ticket ticket3 = new Ticket(3, 900, "LED", "GOJ", 230);
    Ticket ticket4 = new Ticket(4, 950, "LED", "GOJ", 220);

    @Test
    public void shouldFindZero() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        Ticket[] expected = {};
        Ticket[] actual = manager.searchByAirport("LED", "OGZ");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOne() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.searchByAirport("GOJ", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveral() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        Ticket[] expected = {ticket3, ticket4, ticket1};
        Ticket[] actual = manager.searchByAirport("LED", "GOJ");
        Assertions.assertArrayEquals(expected, actual);
    }
}
