/*
 * Auxiliary class for "Week 6 Lecture Day 3 Homework - Control Flow"
 * contains routines for printing out character lists fom ascii table
 *
 * created 15 October 2022
 *
 * @author Len Lutz
 */
public class AsciiChars {
    // prints out numbers (ascii characters 48-57)
    public static void printNumbers()
    {
        for (int i = 48; i < 58; i++) {
            System.out.printf("%c ", i);
        }
        System.out.println();
    }

    // prints out lowercase alphabetic characters (ascii characters 97-122)
    public static void printLowerCase()
    {
        for (int i = 97; i < 123; i++) {
            System.out.printf("%c ", i);
        }
        System.out.println();
    }

    // prints out upercase alphabetic characters (ascii characters 65-90)
    public static void printUpperCase()
    {
        for (int i = 65; i < 91; i++) {
            System.out.printf("%c ", i);
        }
        System.out.println();
    }
}
