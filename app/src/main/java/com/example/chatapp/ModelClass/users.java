package com.example.chatapp.ModelClass;

public class users {
    String status;
    String bio;
    private String id;
    private String username;
    private String imageURL;
    private String EmailId;

    public users(String id, String username, String imageURL, String status, String bio, String EmailID) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
        this.bio = bio;
        this.EmailId = EmailID;
    }

    public users() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
}
