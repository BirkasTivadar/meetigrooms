package meetingrooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MySqlMeetingRoomsRepositoryTest {

    public static final String NAGY_TARGYALO = "NagyTárgyaló";
    public static final String TARGYALO = "Tárgyaló";
    public static final String KIS_TARGYALO = "Kistárgyaló";
    public static final String KONFERENCIA = "Konferencia";

    MySqlMeetingRoomsRepository mySqlMeetingRoomsRepository = new MySqlMeetingRoomsRepository();

    @BeforeEach
    public void init() {
        mySqlMeetingRoomsRepository.save(NAGY_TARGYALO, 6, 10);
        mySqlMeetingRoomsRepository.save(TARGYALO, 5, 8);
        mySqlMeetingRoomsRepository.save(KIS_TARGYALO, 3, 4);
        mySqlMeetingRoomsRepository.save(KONFERENCIA, 10, 10);
    }

    @AfterEach
    public void clear() {
        mySqlMeetingRoomsRepository.deleteAll();
    }


    @Test
    void testGetOrderedNames() {
        List<String> orderedNames = Arrays.asList(
                KIS_TARGYALO,
                KONFERENCIA,
                NAGY_TARGYALO,
                TARGYALO);

        assertEquals(orderedNames, mySqlMeetingRoomsRepository.getOrderedNames());
    }

    @Test
    void testGetReversedNames() {
        List<String> reversedNames = Arrays.asList(
                TARGYALO,
                NAGY_TARGYALO,
                KONFERENCIA,
                KIS_TARGYALO);

        assertEquals(reversedNames, mySqlMeetingRoomsRepository.getReversedNames());
    }

    @Test
    void testGetEvenOrderedNames() {
        List<String> evenOrderedNames = Arrays.asList(
                KONFERENCIA,
                TARGYALO);

        assertEquals(evenOrderedNames, mySqlMeetingRoomsRepository.getEvenOrderedNames());
    }

    @Test
    void testGetMeetingRoomsOrderedByAreaDesc() {
        List<String> namesByOrderedAreaDesc = Arrays.asList(
                KONFERENCIA,
                NAGY_TARGYALO,
                TARGYALO,
                KIS_TARGYALO);

        assertEquals(namesByOrderedAreaDesc, mySqlMeetingRoomsRepository.getMeetingRoomsOrderedByAreaDesc().stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }


    @Test
    void testGetMeetingRoomsWithName() {
        assertEquals(8, mySqlMeetingRoomsRepository.getMeetingRoomsWithName(TARGYALO).get().getLength());
        assertEquals(3, mySqlMeetingRoomsRepository.getMeetingRoomsWithName(KIS_TARGYALO).get().getWidth());
        assertEquals(Optional.empty(), mySqlMeetingRoomsRepository.getMeetingRoomsWithName("Hello"));
    }

    @Test
    void testGetMeetingRoomsContains() {
        List<String> namesMeetingRoomsContains = Arrays.asList(
                KIS_TARGYALO,
                NAGY_TARGYALO,
                TARGYALO
        );
        assertEquals(1, mySqlMeetingRoomsRepository.getMeetingRoomsContains("ferenc").size());
        assertEquals(namesMeetingRoomsContains, mySqlMeetingRoomsRepository.getMeetingRoomsContains("tárgy").stream().map(MeetingRoom::getName).collect(Collectors.toList()));

        assertEquals(Arrays.asList(), mySqlMeetingRoomsRepository.getMeetingRoomsContains("bibi"));

    }

    @Test
    void testGetAreasLargerThan() {
        List<String> namesAreaLargerThan = Arrays.asList(
                NAGY_TARGYALO,
                TARGYALO,
                KONFERENCIA);

        assertEquals(namesAreaLargerThan, mySqlMeetingRoomsRepository.getAreasLargerThan(20).stream().map(MeetingRoom::getName).collect(Collectors.toList()));
        assertEquals(Arrays.asList(), mySqlMeetingRoomsRepository.getAreasLargerThan(1000));
    }
}