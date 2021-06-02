package meetingrooms;

public class MeetingRoomsController {

    private MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MySqlMeetingRoomsRepository());

    public void start() {
        meetingRoomsService.printNames();
    }


    public static void main(String[] args) {

        new MeetingRoomsController().start();
    }
}
