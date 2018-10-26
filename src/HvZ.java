/**
 * @Name    :   Rohan krishna Ramkhumar
 * @Case ID :   rxr353
 * @Project :   HvZ Java Version
 * @Class   :   HvZ-Homework Assignment
 */

import java.util.*;

public class HvZ {

    /**
     * Sorted Set points contains all the values of type zCoordinates
     * Comparator uses a custom compare method to get sort by X Values, then Y
     */
    SortedSet<zCoordinates> points = new TreeSet<>(new Comparator<zCoordinates>() {
        @Override
        public int compare(zCoordinates z1, zCoordinates z2) {
           int result = Double.compare((double)z1.getX(), (double)z2.getX());//First sort by X

           if (result==0)
               result = Double.compare((double)z1.getY(), (double)z2.getY());//If X Values are equal, sort by Y

           return result;
        }
    });

    /**
     * Zombie Method checks if Zombie exists
     * @param x
     * @param y
     * @return Either that the zombie exists, otherwise throws a null exception
     */
    public zCoordinates zombie(int x, int y) {
        zCoordinates tempZ = new zCoordinates(x, y);
        if (points.contains(tempZ))//Same method as any list
            return tempZ;

        throw new NullPointerException("Zombie Does not exist");//Throwing exception cause was not clear wether it needed a statement or an exception
    }

    public void insert(zCoordinates z, int x, int y) {

        Objects.requireNonNull(z, "Zombie is Null");

        z = new zCoordinates(x,y);

        if (points.contains(z))
            throw new IllegalArgumentException("Zombie already exists at Coordinate");
        else
            points.add(z);
    }

    /**
     * Deletes the value from the List
     * Creates a new Zombie and then checks if it's contained in the list
     * @param x
     * @param y
     * @return type zombie or exception
     */
    public zCoordinates delete(int x, int y) {
        zCoordinates tempZ = new zCoordinates(x, y);
        if (points.contains(tempZ)) {
            points.remove(tempZ);//Standard Set/List Function
            return tempZ;
        }

        throw new NullPointerException("No Zombie exists here");
    }

    /**
     * Since the list is already sorted, all we need is return the first
     * or last value in the list
     * @param Direction
     * @return String x and y coordinates of the correct values
     */
    public String arrow(String Direction) {
        if (Direction.equals("left"))
            return points.first().getCoordinates();//Already sorted so doesn't matter
        else if(Direction.equals("right"))
            return points.last().getCoordinates();//Already sorted so doesn't matter

        throw new NullPointerException("No Zombies Exist");
    }


    /**
     * Method shows the closest coordinate to the player
     * It uses an iterator and a descending iterator to make it work
     * @param xp
     * @return the coordinates
     */
    public String javelin(int xp) {
        zCoordinates z = null;
        int closestX = Math.abs(points.first().getX()-xp);
        //The left to player coordinate range
        Iterator<zCoordinates> iter = points.iterator();

        while (iter.hasNext()){//Iterates through, stores difference and distance
            zCoordinates tempZ = iter.next();
            int diff = Math.abs(tempZ.getX()-xp);
            if (diff<=closestX){
                closestX = diff;
                z = tempZ;

            }
        }

        Objects.requireNonNull(z, "No Zombies available");

        return (z.getCoordinates());

    }


    /**
     * Bomb takes the range, iterates through from range to xp on both sides
     * Then compares the min and max to find the farther one
     * @param xp player x coordinate
     * @param r Range to which player can throw
     * @return maximum range value
     */
    public String bomb(int xp, int r) {
        zCoordinates minZ = null;
        zCoordinates maxZ = null;

        Iterator<zCoordinates> iter = points.iterator();//maxValue

        maxZ = bombHelperUp(xp+r, iter);//Calls the bomb Helper

        LinkedList<zCoordinates> list = new LinkedList<>(points);

        Iterator<zCoordinates> iterReverse = list.descendingIterator();

        minZ = bombHelperDown(xp-r, iterReverse);//minValue

        //compares to return the correct valye
        if (Math.abs(maxZ.getY())>Math.abs(minZ.getY()))
            return (maxZ.getCoordinates());

        else
            return (minZ.getCoordinates());
    }

    /**
     * Simple iterator helper method for scrolling to the left of the given X Value
     * @param range
     * @param iter
     * @return
     */
    private zCoordinates bombHelperUp(int range, Iterator<zCoordinates> iter){
        zCoordinates result = null;

        while (iter.hasNext()){
            zCoordinates tempZ = iter.next();
            if (tempZ.getX()<=range)
                result = tempZ;
        }

        return result;
    }

    /**
     * Simple iterator helper method for scrolling to the right of the given X Value
     * @param range
     * @param iter
     * @return
     */
    private zCoordinates bombHelperDown(int range, Iterator<zCoordinates> iter){
        zCoordinates result = null;

        while (iter.hasNext()){
            zCoordinates tempZ = iter.next();
            if (tempZ.getX()>=range)
                result = tempZ;
        }


        return result;
    }



}
