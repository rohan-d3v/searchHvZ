/**
 *
 * @Name    :   Rohan krishna Ramkhumar
 * @Case ID :   rxr353
 * @Project :   HvZ Java Version
 * @Class   :   Helper class
 *
 * Simple zombie coordinate class used to return x and y coordinates
 * Also has the return string version method to make it easier to read in main class
 */
public class zCoordinates {
    public final int x,y;

    /**
     * Just a 2Coordinate type
     * @param x
     * @param y
     */
    public zCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCoordinates(){
        return (x+","+y);
    }


}
