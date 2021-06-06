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

    MySqlMeetingRoomsRepository mySqlMeetingRoomsRepository = new MySqlMeetingRoomsRepository();

    @BeforeEach
    public void init() {
        mySqlMeetingRoomsRepository.save("Nagytárgyaló", 6, 10);
        mySqlMeetingRoomsRepository.save("Tárgyaló", 5, 8);
        mySqlMeetingRoomsRepository.save("Kistárgyaló", 3, 4);
        mySqlMeetingRoomsRepository.save("Konferencia", 10, 10);
    }

    @AfterEach
    public void clear() {
        mySqlMeetingRoomsRepository.deleteAll();
    }


    @Test
    void testGetOrderedNames() {
        List<String> orderedNames = Arrays.asList(
                "Kistárgyaló",
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló");

        assertEquals(orderedNames, mySqlMeetingRoomsRepository.getOrderedNames());
    }

    @Test
    void testGetReversedNames() {
        List<String> reversedNames = Arrays.asList(
                "Tárgyaló",
                "Nagytárgyaló",
                "Konferencia",
                "Kistárgyaló");

        assertEquals(reversedNames, mySqlMeetingRoomsRepository.getReversedNames());
    }

    @Test
    void testGetEvenOrderedNames() {
        List<String> evenOrderedNames = Arrays.asList(
                "Konferencia",
                "Tárgyaló");

        assertEquals(evenOrderedNames, mySqlMeetingRoomsRepository.getEvenOrderedNames());
    }

    @Test
    void testGetMeetingRoomsOrderedByAreaDesc() {
        List<String> namesByOrderedAreaDesc = Arrays.asList(
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló",
                "Kistárgyaló");

        assertEquals(namesByOrderedAreaDesc, mySqlMeetingRoomsRepository.getMeetingRoomsOrderedByAreaDesc().stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }


    @Test
    void testGetMeetingRoomsWithName() {
        assertEquals(8, mySqlMeetingRoomsRepository.getMeetingRoomsWithName("Tárgyaló").get().getLength());
        assertEquals(3, mySqlMeetingRoomsRepository.getMeetingRoomsWithName("Kistárgyaló").get().getWidth());
        assertEquals(Optional.empty(), mySqlMeetingRoomsRepository.getMeetingRoomsWithName("Hello"));
    }


    @Test
    void testGetMeetingRoomsContains() {
        assertEquals(1, mySqlMeetingRoomsRepository.getMeetingRoomsContains("ferenc").size());
        assertEquals(Arrays.asList(), mySqlMeetingRoomsRepository.getMeetingRoomsContains("bibi"));

    }

    @Test
    void testGetAreasLargerThan() {
        List<String> namesAreaLargerThan = Arrays.asList(
                "Nagytárgyaló",
                "Tárgyaló",
                "Konferencia");

        assertEquals(namesAreaLargerThan, mySqlMeetingRoomsRepository.getAreasLargerThan(20).stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }
}