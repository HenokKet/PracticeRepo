public class Locker {
    private String lockerId;
    private boolean isOccupied;
    private String contents;

    public Locker(String lockerId) {
        this.lockerId = lockerId;
        this.isOccupied = false;
        this.contents = "";
    }

    public String getLockerId() {
        return lockerId;
    }

    public void storeItem(String item) {
        this.contents = item;
        this.isOccupied = true;
    }

    public void removeItem() {
        this.contents = "";
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Locker ID: " + lockerId +
                ", Occupied: " + isOccupied +
                ", Contents: " + (contents.isEmpty() ? "Empty" : contents);
    }

    @Override
    public boolean equals(Object obj){
        if(this.getClass() == obj.getClass()) {
            Locker locker = (Locker) obj;
            return this.lockerId.equals(locker.lockerId)
                    && this.isOccupied == locker.isOccupied
                    && this.contents.equals(locker.contents);
        }
        return false;
    }
}
