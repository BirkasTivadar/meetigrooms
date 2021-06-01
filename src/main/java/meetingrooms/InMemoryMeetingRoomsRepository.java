package meetingrooms;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository {

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    private List<String> orderedByHungarianName() {
        List<String> names = meetingRooms.stream().map(e -> e.getName()).collect(Collectors.toList());
        Collections.sort(names, Collator.getInstance(new Locale("hu", "HU")));
        return names;
    }


    @Override
    public void printNames() {
        orderedByHungarianName().forEach(e -> System.out.println(e));

    }

    @Override
    public void printNamesReverse() {
        orderedByHungarianName().stream().sorted(Comparator.reverseOrder()).forEach(e -> System.out.println(e));
    }

    @Override
    public void printEvenNames() {

    }

    @Override
    public void printAreas() {
        meetingRooms.stream().sorted(Comparator.comparing(e -> e.getArea())).forEach(MeetingRoom::printAll);
    }

    @Override
    public void printMeetingRoomsWithName(String name) {
        meetingRooms.stream().filter(e -> e.getName().equals(name)).forEach(MeetingRoom::printAll);
    }

    @Override
    public void printMeetingRoomsContains(String part) {
        meetingRooms.stream().filter(e -> e.getName().toLowerCase().contains(part.toLowerCase())).forEach(MeetingRoom::printDimensions);
    }

    @Override
    public void printAreasLargerThan(int area) {
        meetingRooms.stream().filter(e -> e.getArea() > area).forEach(MeetingRoom::printAll);
    }
}
