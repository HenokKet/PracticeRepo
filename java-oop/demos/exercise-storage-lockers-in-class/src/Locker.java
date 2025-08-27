import java.util.concurrent.locks.Lock;

public class Locker {
    private String lockerId;
    private boolean isOccupied;
    private String contents;

    //Constructor
    public Locker (String lockerId) {
        this.lockerId = lockerId;
        this.isOccupied = false;
        this.contents = "";
    }

    //Getters and Setters
    public String getLockerId() {
        return lockerId;
    }

    //Methods
    public void storeItem(String contents){
        this.contents = contents;
        this.isOccupied = true;
    }

    public void removeItem() {
        this.contents = "";
        this.isOccupied = false;
    }

    @Override
    public String toString(){
        return "Locker ID: " + lockerId +
                "\nOccupied: " + isOccupied +
                "\nContents: " + contents;
    }

    @Override
    public boolean equals(Object obj){
        if (this.getClass() == obj.getClass()) {
            Locker locker = (Locker) obj;
            return this.lockerId.equals(locker.lockerId)
                    && this.isOccupied == locker.isOccupied
                    && this.contents.equals(locker.contents);
        }
        return false;
    }


}
