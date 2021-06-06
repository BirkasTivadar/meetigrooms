package meetingrooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeetingRoomsTest {

    InMemoryMeetingRooms inMemoryMeetingRooms = new InMemoryMeetingRooms();


    @BeforeEach
    public void init() {
        inMemoryMeetingRooms.save("Nagytárgyaló", 6, 10);
        inMemoryMeetingRooms.save("Tárgyaló", 5, 8);
        inMemoryMeetingRooms.save("Kistárgyaló", 3, 4);
        inMemoryMeetingRooms.save("Konferencia", 10, 10);
    }

    @AfterEach
    public void clear() {
        inMemoryMeetingRooms.deleteAll();
    }

    @Test
    void testGetOrderedNames() {
        List<String> orderedNames = Arrays.asList(
                "Kistárgyaló",
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló");

        assertEquals(orderedNames, inMemoryMeetingRooms.getOrderedNames());
    }

    @Test
    void testGetReversedNames() {
        List<String> reversedNames = Arrays.asList(
                "Tárgyaló",
                "Nagytárgyaló",
                "Konferencia",
                "Kistárgyaló");

        assertEquals(reversedNames, inMemoryMeetingRooms.getReversedNames());
    }

    @Test
    void getEvenOrderedNames() {
        List<String> evenOrderedNames = Arrays.asList(
                "Konferencia",
                "Tárgyaló");

        assertEquals(evenOrderedNames, inMemoryMeetingRooms.getEvenOrderedNames());
    }


    @Test
    void getMeetingRoomsOrderedByAreaDesc() {
        List<String> namesByOrderedAreaDesc = Arrays.asList(
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló",
                "Kistárgyaló");

        assertEquals(namesByOrderedAreaDesc, inMemoryMeetingRooms.getMeetingRoomsOrderedByAreaDesc().stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }

    @Test
    void getMeetingRoomsWithName() {
        assertEquals(8, inMemoryMeetingRooms.getMeetingRoomsWithName("Tárgyaló").get().getLength());
        assertEquals(3, inMemoryMeetingRooms.getMeetingRoomsWithName("Kistárgyaló").get().getWidth());
        assertEquals(Optional.empty(), inMemoryMeetingRooms.getMeetingRoomsWithName("Hello"));
    }

    @Test
    void getMeetingRoomsContains() {

    }

    @Test
    void getAreasLargerThan() {
    }


}