package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<String> getOrderedNames();

    List<String> getReversedNames();

    List<String> getEvenOrderedNames();

    List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc();

    MeetingRoom getMeetingRoomsWithName(String name);

    MeetingRoom getMeetingRoomsContains(String part);

    List<MeetingRoom> getAreasLargerThan(int area);

    void deleteAll();

}
