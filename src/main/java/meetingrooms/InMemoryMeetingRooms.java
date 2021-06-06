package meetingrooms;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRooms implements MeetingRoomsRepository {

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));

    }

    @Override
    public List<String> getOrderedNames() {
        return meetingRooms.stream().map(MeetingRoom::getName).sorted(Collator.getInstance(new Locale("hu", "HU"))).collect(Collectors.toList());
    }

    @Override
    public List<String> getReversedNames() {
        return meetingRooms.stream().map(MeetingRoom::getName).sorted(Collator.getInstance(new Locale("hu", "HU")).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<String> getEvenOrderedNames() {
        List<String> orderedNames = this.getOrderedNames();
        return IntStream.range(0, orderedNames.size()).filter(i -> i % 2 != 0).mapToObj(orderedNames::get).collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc() {
        return meetingRooms.stream().sorted(Comparator.comparing((MeetingRoom::getArea)).reversed()).collect(Collectors.toList());
    }

    @Override
    public Optional<MeetingRoom> getMeetingRoomsWithName(String name) {
        return meetingRooms.stream().filter(e -> e.getName().equals(name)).findAny();
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsContains(String part) {
        return meetingRooms.stream().filter(e -> e.getName().toLowerCase().contains(part.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getAreasLargerThan(int area) {
        return meetingRooms.stream().filter(e -> e.getArea() > area).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        meetingRooms = new ArrayList<>();
    }
}
