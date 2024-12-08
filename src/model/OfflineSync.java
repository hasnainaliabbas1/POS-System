package model;

public class OfflineSync {
    private int id;
    private boolean syncRequired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSyncRequired() {
        return syncRequired;
    }

    public void setSyncRequired(boolean syncRequired) {
        this.syncRequired = syncRequired;
    }
}
