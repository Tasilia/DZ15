import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public Ticket[] searchByAirport(String departureAirport, String arrivalAirport) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        Ticket[] tmp = new Ticket[0];
        for (Ticket ticket : repo.getTickets()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                tmp = Arrays.copyOf(result, result.length + 1);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        Ticket[] tmp = new Ticket[0];
        for (Ticket ticket : repo.getTickets()) {
            if (matches(ticket, from, to)) {
                tmp = Arrays.copyOf(result, result.length + 1);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public boolean matches(Ticket ticket, String requiredDepartureAirport, String requiredArrivalAirport) {
        if (ticket.getDepartureAirport().contains(requiredDepartureAirport) &&
                ticket.getArrivalAirport().contains(requiredArrivalAirport)) {
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] getRepo() {
        return repo.getTickets();
    }
}
