public class PlayerLevel {
    public static void main(String[] args) {
        //    Declare and initialize variables:
        int level =8;
        int currentXP = 2000;
        int xpToNextLevel = 2500;
//     1. Use Arithmetic Operators:
//            Add +300 XP when completing a quest (+=).
//            Reduce xpToNextLevel dynamically as XP increases (-=).
//            Multiply XP if player earns a double XP boost (*=).
        currentXP += 300;
        xpToNextLevel -= 300;
        currentXP *= 2;
//     2. Use Comparison Operators:
//            Check if currentXP is greater than or equal to xpToNextLevel.
//            Check if the player has reached Level 10.
        boolean xpRequirementMet = currentXP >= xpToNextLevel;
        boolean reachedLevel10 = level >= 10;
//      3. Use Logical Operators:
//            Determine if the player levels up (XP requirement met AND level < 10).
//            Determine if the player is a pro (Level > 7 OR XP over 5000).
       boolean canLevelUp;
        if (xpRequirementMet && level < 10) {
            level++;
            currentXP -= xpToNextLevel;
            xpToNextLevel += 1000;
            canLevelUp = true;
        }else {
            canLevelUp = false;
        }
        boolean isPro = (level > 7) || (currentXP > 5000);

//       4. Print the updated values and level-up status.
        System.out.println("\nUpdated Status ");
        System.out.println("Level: " + level);
        System.out.println("Current XP: " + currentXP);
        System.out.println("XP to Next Level: " + xpToNextLevel);
        System.out.println("Can Level Up? " + canLevelUp);
        System.out.println("Is Player a Pro? " + isPro);
    }
}
