package meetingrooms;

import java.util.List;
import java.util.Optional;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length) {
        meetingRoomsRepository.save(name, width, length);
    }

    public List<String> getOrderedNames() {
        return meetingRoomsRepository.getOrderedNames();
    }

    public List<String> getReversedNames() {
        return meetingRoomsRepository.getReversedNames();
    }

    public List<String> getEvenOrderedNames() {
        return meetingRoomsRepository.getEvenOrderedNames();
    }

    public List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc() {
        return meetingRoomsRepository.getMeetingRoomsOrderedByAreaDesc();
    }

    public Optional<MeetingRoom> getMeetingRoomsWithName(String name) {
        return meetingRoomsRepository.getMeetingRoomsWithName(name);
    }

    public List<MeetingRoom> getMeetingRoomsContains(String part) {
        return meetingRoomsRepository.getMeetingRoomsContains(part);
    }

    public List<MeetingRoom> getAreasLargerThan(int area) {
        return meetingRoomsRepository.getAreasLargerThan(area);
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
