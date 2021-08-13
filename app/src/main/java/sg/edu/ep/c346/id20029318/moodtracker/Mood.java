package sg.edu.ep.c346.id20029318.moodtracker;

import java.io.Serializable;

public class Mood implements Serializable{

    private int id;
    private String name;
    private String description;
    private String mood;
    private String date;

   public Mood (int id, String name, String description, String mood, String date) {
       this.id = id;
       this.name = name;
       this.description = description;
       this.mood = mood;
       this.date = date;

   }

    public Mood (String name, String description, String mood, String date) {
        this.name = name;
        this.description = description;
        this.mood = mood;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
