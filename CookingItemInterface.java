//Yu-chi Chang, William Shyr
//ychang64, wshyr1
//EN.600.226
//CookingItemInterface.java
//p1


public interface CookingItemInterface {
    void tick(); // Implements a simulation of one minute of time for this item 
                     // by decrementing cooking time by one minute
    int timeRemaining(); // Returns time remaining for this dish
    int penalty(); // Returns the penalty if this dish were removed now
}