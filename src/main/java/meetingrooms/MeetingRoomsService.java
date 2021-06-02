package meetingrooms;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length){
        meetingRoomsRepository.save(name, width, length);
    }

    public void printNames(){
        meetingRoomsRepository.printNames();
    }
}
