package beans;

public class Reaction {
    private int taskId;
    private String userName;
    private String emoji;

    public Reaction(int taskId, String userName, String emoji) {
        this.taskId = taskId;
        this.userName = userName;
        this.emoji = emoji;
    }

    public int getTaskId() { return taskId; }
    public String getUserName() { return userName; }
    public String getEmoji() { return emoji; }

}