package meetingrooms;

import java.util.List;
import java.util.Optional;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<String> getOrderedNames();

    List<String> getReversedNames();

    List<String> getEvenOrderedNames();

    List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc();

    Optional<MeetingRoom> getMeetingRoomsWithName(String name);

    List<MeetingRoom> getMeetingRoomsContains(String part);

    List<MeetingRoom> getAreasLargerThan(int area);

    void deleteAll();

}
