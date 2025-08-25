public class Deadline extends Task {
    public String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

    }


    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by:" + deadline + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }



}
