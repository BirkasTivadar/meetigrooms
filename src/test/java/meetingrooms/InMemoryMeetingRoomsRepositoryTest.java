package meetingrooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeetingRoomsRepositoryTest {

    public static final String NAGY_TARGYALO = "NagyTárgyaló";
    public static final String TARGYALO = "Tárgyaló";
    public static final String KIS_TARGYALO = "Kistárgyaló";
    public static final String KONFERENCIA = "Konferencia";

    InMemoryMeetingRoomsRepository inMemoryMeetingRoomsRepository = new InMemoryMeetingRoomsRepository();

    @BeforeEach
    public void init() {
        inMemoryMeetingRoomsRepository.save(NAGY_TARGYALO, 6, 10);
        inMemoryMeetingRoomsRepository.save(TARGYALO, 5, 8);
        inMemoryMeetingRoomsRepository.save(KIS_TARGYALO, 3, 4);
        inMemoryMeetingRoomsRepository.save(KONFERENCIA, 10, 10);
    }

    @AfterEach
    public void clear() {
        inMemoryMeetingRoomsRepository.deleteAll();
    }

    @Test
    void testGetOrderedNames() {
        List<String> orderedNames = Arrays.asList(
                KIS_TARGYALO,
                KONFERENCIA,
                NAGY_TARGYALO,
                TARGYALO);

        assertEquals(orderedNames, inMemoryMeetingRoomsRepository.getOrderedNames());
    }

    @Test
    void testGetReversedNames() {
        List<String> reversedNames = Arrays.asList(
                TARGYALO,
                NAGY_TARGYALO,
                KONFERENCIA,
                KIS_TARGYALO);

        assertEquals(reversedNames, inMemoryMeetingRoomsRepository.getReversedNames());
    }

    @Test
    void getEvenOrderedNames() {
        List<String> evenOrderedNames = Arrays.asList(
                KONFERENCIA,
                TARGYALO);

        assertEquals(evenOrderedNames, inMemoryMeetingRoomsRepository.getEvenOrderedNames());
    }

    @Test
    void getMeetingRoomsOrderedByAreaDesc() {
        List<String> namesByOrderedAreaDesc = Arrays.asList(
                KONFERENCIA,
                NAGY_TARGYALO,
                TARGYALO,
                KIS_TARGYALO);

        assertEquals(namesByOrderedAreaDesc, inMemoryMeetingRoomsRepository.getMeetingRoomsOrderedByAreaDesc().stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }

    @Test
    void getMeetingRoomsWithName() {
        assertEquals(8, inMemoryMeetingRoomsRepository.getMeetingRoomsWithName(TARGYALO).get().getLength());
        assertEquals(3, inMemoryMeetingRoomsRepository.getMeetingRoomsWithName(KIS_TARGYALO).get().getWidth());
        assertEquals(Optional.empty(), inMemoryMeetingRoomsRepository.getMeetingRoomsWithName("Hello"));
    }

    @Test
    void getMeetingRoomsContains() {
        List<String> namesMeetingRoomsContains = Arrays.asList(
                KIS_TARGYALO,
                NAGY_TARGYALO,
                TARGYALO
        );

        assertEquals(1, inMemoryMeetingRoomsRepository.getMeetingRoomsContains("ferenc").size());
        assertEquals(namesMeetingRoomsContains, inMemoryMeetingRoomsRepository.getMeetingRoomsContains("tárgy").stream().map(MeetingRoom::getName).collect(Collectors.toList()));
        assertEquals(Arrays.asList(), inMemoryMeetingRoomsRepository.getMeetingRoomsContains("bibi"));
    }

    @Test
    void getAreasLargerThan() {
        List<String> namesAreaLargerThan = Arrays.asList(
                NAGY_TARGYALO,
                TARGYALO,
                KONFERENCIA);

        assertEquals(namesAreaLargerThan, inMemoryMeetingRoomsRepository.getAreasLargerThan(20).stream().map(MeetingRoom::getName).collect(Collectors.toList()));
        assertEquals(Arrays.asList(), inMemoryMeetingRoomsRepository.getAreasLargerThan(1000));
    }
}