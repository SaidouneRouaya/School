package Entities;


public class Presence {

    public int id_session;
    public boolean present;

    public Presence(int id_session, boolean present) {
        this.id_session = id_session;
        this.present = present;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}