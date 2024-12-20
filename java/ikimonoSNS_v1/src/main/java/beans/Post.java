package beans;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String userId;
    private String userName;
    private String caption;
    private String dateTime;
    private String imageUrl; // 画像URL
    private String category;
    private int categoryId; // カテゴリID
    private int totalStamp;

    public Post() {}

    public Post(int id, String userId, String userName, String caption, String dateTime, String imageUrl,String category, int totalStamp) {
    	this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.caption = caption;
        this.dateTime = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm"));
        this.imageUrl = imageUrl;
        this.category = category;
        this.totalStamp = totalStamp;
        
    }

    public Post(String userId, String userName, String caption, String dateTime, String imageUrl, int categoryId) {
        this.userId = userId;
        this.userName = userName;
        this.caption = caption;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    public Post(String userName, String caption, String imageUrl, int categoryId) {
        this.userName = userName;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId; // 初期化
    }

	public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getCaption() { return caption; }
    public String getDateTime() { return dateTime; }
    public String getImageUrl() { return imageUrl; }
    public String getCategory() { return category; }
    public int getCategoryId() { return categoryId; }
    public int getTotalStamp() { return totalStamp; }

    public void setId(int id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setCaption(String caption) { this.caption = caption; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setCategory(String category) { this.category = category; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setTotalStamp(int totalStamp) { this.totalStamp = totalStamp; }
}
