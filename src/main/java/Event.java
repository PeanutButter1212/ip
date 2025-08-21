public class Event extends Task{
    protected String startDate;
    protected String deadline;

    public Event(String description, String deadline, String startDate ) {
        super(description);
        this.deadline = deadline;
        this.startDate = startDate;


    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from:" + startDate + "to:" + deadline + ")";
    }
}
