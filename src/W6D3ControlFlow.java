import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Main class for "Week 6 Lecture Day 3 Homework - Control Flow"
 * created 15 October 2022
 *
 * @author Len Lutz
 */
public class W6D3ControlFlow {
    // max number of white and red balls for each lottery
    static final int PB_WHITE_MAX = 69;   // PowerBall
    static final int PB_RED_MAX = 26;

    // these are not used now, but we define them in case we want
    // to update the program to generate numbers for other
    // lotteries in the future
    static final int MM_WHITE_MAX = 70;   // Mega Millions
    static final int MM_RED_MAX = 24;
    static final int LA_WHITE_MAX = 52;   // Lotto America
    static final int LA_RED_MAX = 10;

    // create arrayList to store generated numbers
    static ArrayList<Integer> whiteBalls = new ArrayList<>(5);

    // set up scanner to gather information from user
    static Scanner scanner = new Scanner(System.in);

    /**
     * Method: addWhiteBall
     *
     * Purpose: This method validates the integer passed in to make sure
     *          it has not yet been added to the arrayList.  If it has,
     *          it increments the number until it finds a number that has
     *          not been added.  If it reaches the maximum number allowed,
     *          it divides the number by three and continues searching for
     *          an available number frm there.  Once it finds an available
     *          number, it adds it to the arrayList.
     *
     * @param whiteBall - integer containing number to be added to
     *         whiteBalls arrayList
     *
     */
    public static void addWhiteBall(Integer whiteBall) {
        // check if number is already in arrayList
        while (whiteBalls.contains(whiteBall)) {
            // if maximum number has been reached, start with lower number
            if (whiteBall == PB_WHITE_MAX) {
                whiteBall /= 3;
            }
            // increment number until unused number has been found
            whiteBall++;
        }
        // add number to arrayList
        whiteBalls.add(whiteBall);
    }

    /**
     * Method: addWhiteBall
     *
     * Purpose: Processes numeric input.  If number has not been
     *          entered, or if number is invalid, it uses the
     *          supplied default number.
     *
     * @param defaultNum - integer containing default number if user does not
     *          enter a number or entered number is invalid
     *
     * @return: Returns entered (or default) number
     *
     */
    public static int getNumInput(Integer defaultNum) {
        // create local variables
        Integer result;
        String userInput;

        // read in line rom console
        userInput = scanner.nextLine();
        if (userInput.isEmpty()) {
            // if nothing was entered, use default number
            result = defaultNum;
        } else {
            try {
                // if number was entered, store it in result
                result = Math.abs(Integer.parseInt(userInput));
            } catch (Exception e) {
                // if what was entered cannot be parsed to a number,
                // use default
                result = defaultNum;
            }
        }
        return result;
    }

    /**
     * method: main - routine that starts the program
     *
     * @param args - the command line arguments
     *     No arguments are expected or checked for in this program
     */
    public static void main(String[] args) {
        // call routines to output ascii characters (part of assignment)
        AsciiChars.printNumbers();
        AsciiChars.printLowerCase();
        AsciiChars.printUpperCase();

        // Get user's name
        System.out.print("Please enter your name: ");
        String userName = scanner.nextLine();

        // greet user
        System.out.println("Hello, " + userName);

        // ask if user wants to continue and get response
        System.out.print("Would you like to continue to the interactive portion? y/n: ");
        String yesNo = scanner.nextLine().toLowerCase();
        if(yesNo.isEmpty()) {
            yesNo = "n";
        }

        // if user doesn't answer or doesn't indicate yes, end program
        if (yesNo.charAt(0) != 'y') {
            System.out.println("Please return later to complete the survey. ");
            return;
        }

        // create program variables
        int redBall;
        String petName;
        int petAge;
        int luckyNumber;
        int jerseyNum = 12;
        int carYear;
        String favActor;
        int userRandomNum;
        int randomRed;
        int randomNum;
        int whiteBall;

        // start continuous loop to run program until user does NOT indicate yes
        while(yesNo.charAt(0) == 'y') {
            // clear arrayList at beginning of each loop
            whiteBalls.clear();

            // generate new random numbers for each run
            randomRed = ThreadLocalRandom.current().nextInt(1, PB_RED_MAX + 1);
            randomNum = ThreadLocalRandom.current().nextInt(1, PB_WHITE_MAX + 1);

            // gather information from user
            System.out.print("What is the name of your favorite pet? ");
            petName = scanner.nextLine();
            if(petName.isEmpty()) {
                petName = "Jagger";
            }

            System.out.print("What is your favorite pet's age? ");
            petAge = getNumInput(10);

            System.out.print("What is your lucky number? ");
            luckyNumber = getNumInput(13);

            System.out.print("Do you have a favorite quarterback? ");
            yesNo = scanner.nextLine().toLowerCase();
            if(yesNo.isEmpty()) {
                yesNo = "n";
            }
            if (yesNo.charAt(0) == 'y') {
                System.out.print("What is their jersey number?");
                jerseyNum = getNumInput(12);
            }

            System.out.print("What is the two-digit model year of your car? ");
            carYear = getNumInput(73) % 100;

            System.out.print("What is the first name of your favorite actor/actress? ");
            favActor = scanner.nextLine();
            if (favActor.isEmpty()) {
                favActor = "Harrison";
            }

            System.out.print("Please enter a number between 1 and 50: ");
            userRandomNum = getNumInput(25) % 50;


            // calculate numbers using entered information
            redBall = (jerseyNum * randomRed) % PB_RED_MAX;

            // add first white ball
            whiteBall = petName.length() > 2 ? (int) petName.charAt(3) : (int) petName.charAt(0);
            whiteBall %= PB_WHITE_MAX;
            whiteBalls.add(whiteBall);

            // add second white ball
            whiteBall = (carYear + luckyNumber) % PB_WHITE_MAX;
            if (whiteBall < 1) {
                whiteBall = luckyNumber % PB_WHITE_MAX;
            }
            addWhiteBall(whiteBall);

            // add third white ball
            whiteBall = Math.abs(userRandomNum - randomNum);
            if (whiteBall < 1) {
                whiteBall = 2;
            }
            addWhiteBall(whiteBall);

            // add fourth white ball
            whiteBall = (jerseyNum + petAge + luckyNumber) % PB_WHITE_MAX;
            addWhiteBall(whiteBall);

            // add fifth (last) white ball
            whiteBall = (int) favActor.charAt(0) % PB_WHITE_MAX;
            addWhiteBall(whiteBall);

            // sort arrayList
            Collections.sort(whiteBalls);

            // display generated numbers
            System.out.printf("\nLottery Numbers: %d, %d, %d, %d, %d    Magic Ball: %d\n",
                    whiteBalls.get(0),
                    whiteBalls.get(1),
                    whiteBalls.get(2),
                    whiteBalls.get(3),
                    whiteBalls.get(4),
                    redBall);

            // print out the entries (or defaults) we have gathered
            // ONLY USED DURING TESTING
//            System.out.println("\nPet's name: " + petName);
//            System.out.println("Pet's age: " + petAge);
//            System.out.println("Lucky Number: " + luckyNumber);
//            System.out.println("QB Jersey Number: " + jerseyNum);
//            System.out.printf("Car's year: %02d\n", carYear);
//            System.out.println("Favorite Actor/Actress first Name: " + favActor);
//            System.out.println("Random Number: " + userRandomNum);

            // ask if user wishes to generate more lottery numbers
            System.out.print("\nWould you like to answer questions again to generate new numbers? y/n: ");

            // get response and continue or end program
            yesNo = scanner.nextLine().toLowerCase();
            if(yesNo.isEmpty()) {
                yesNo = "n";
            }
        }
    }
}
