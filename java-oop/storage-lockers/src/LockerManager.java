import  java.util.ArrayList;
import java.util.List;
public class LockerManager {
    private List<Locker> lockers;

    public LockerManager(){
        lockers = new ArrayList<>();
    }

    void addLocker(String lockerId){
        boolean duplicate = false;
        for(Locker locker: lockers){
            if(locker.getLockerId().equalsIgnoreCase(lockerId)){
                duplicate = true;
                break;
            }
        }
        if(!duplicate) {
            Locker locker = new Locker(lockerId);
            lockers.add(locker);
        }
    }

    Locker getLocker(String lockerId){
      for(Locker locker: lockers){
          if(locker.getLockerId().equalsIgnoreCase(lockerId)){
              return locker;
          }
      }
      return null;
    }

    public void removeLocker(String lockerId){
        for(Locker locker: lockers){
            if(locker.getLockerId().equalsIgnoreCase(lockerId)){
                lockers.remove(locker);
                break;
            }
        }
    }

    public void displayLockers(){
        for(Locker locker: lockers){
            System.out.println(locker.toString());
        }
    }
}
