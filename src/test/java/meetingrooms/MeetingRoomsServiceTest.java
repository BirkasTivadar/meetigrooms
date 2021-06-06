package meetingrooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsServiceTest {

    private MeetingRoomsService meetingRoomsServiceWithMySql = new MeetingRoomsService(new MySqlMeetingRoomsRepository());

    private MeetingRoomsService meetingRoomsServiceInMemory = new MeetingRoomsService(new InMemoryMeetingRoomsRepository());

    public static final String NAGY_TARGYALO = "NagyTárgyaló";
    public static final String TARGYALO = "Tárgyaló";
    public static final String KIS_TARGYALO = "Kistárgyaló";
    public static final String KONFERENCIA = "Konferencia";

    @BeforeEach
    public void init() {
        meetingRoomsServiceWithMySql.save(NAGY_TARGYALO, 6, 10);
        meetingRoomsServiceWithMySql.save(TARGYALO, 5, 8);
        meetingRoomsServiceWithMySql.save(KIS_TARGYALO, 3, 4);
        meetingRoomsServiceWithMySql.save(KONFERENCIA, 10, 10);

        meetingRoomsServiceInMemory.save(NAGY_TARGYALO, 6, 10);
        meetingRoomsServiceInMemory.save(TARGYALO, 5, 8);
        meetingRoomsServiceInMemory.save(KIS_TARGYALO, 3, 4);
        meetingRoomsServiceInMemory.save(KONFERENCIA, 10, 10);
    }

    @AfterEach
    public void clear() {
        meetingRoomsServiceWithMySql.deleteAll();
        meetingRoomsServiceInMemory.deleteAll();
    }
    @Test
    void getOrderedNames() {
        assertEquals(meetingRoomsServiceWithMySql.getOrderedNames(), meetingRoomsServiceInMemory.getOrderedNames());
    }

    @Test
    void getReversedNames() {
        assertEquals(meetingRoomsServiceWithMySql.getReversedNames(), meetingRoomsServiceInMemory.getReversedNames());
    }

    @Test
    void getEvenOrderedNames() {
        assertEquals(meetingRoomsServiceWithMySql.getEvenOrderedNames(), meetingRoomsServiceInMemory.getEvenOrderedNames());
    }

    @Test
    void getMeetingRoomsOrderedByAreaDesc() {
        assertEquals(meetingRoomsServiceWithMySql.getMeetingRoomsOrderedByAreaDesc().get(0).getArea(),meetingRoomsServiceInMemory.getMeetingRoomsOrderedByAreaDesc().get(0).getArea() );
        assertEquals(meetingRoomsServiceWithMySql.getMeetingRoomsOrderedByAreaDesc().get(2).getName(), meetingRoomsServiceInMemory.getMeetingRoomsOrderedByAreaDesc().get(2).getName());
    }

    @Test
    void getMeetingRoomsWithName() {
        assertEquals(meetingRoomsServiceWithMySql.getMeetingRoomsWithName(KIS_TARGYALO).get().getArea(), meetingRoomsServiceInMemory.getMeetingRoomsWithName(KIS_TARGYALO).get().getArea());
    }

    @Test
    void getMeetingRoomsContains() {
        assertEquals(meetingRoomsServiceWithMySql.getMeetingRoomsContains("tárgy").size(), meetingRoomsServiceInMemory.getMeetingRoomsContains("tárgy").size());
        assertEquals(meetingRoomsServiceWithMySql.getMeetingRoomsContains("Tárgy").get(1).getName(), meetingRoomsServiceInMemory.getMeetingRoomsContains("Tárgy").get(1).getName());
    }

    @Test
    void getAreasLargerThan() {
        assertEquals(meetingRoomsServiceWithMySql.getAreasLargerThan(20).size(), meetingRoomsServiceInMemory.getAreasLargerThan(20).size());
    }
}