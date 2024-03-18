package Projects;

import java.util.Scanner;

/**
 This class encapsulates a number of methods and parameters used to organize an assignment of band members that can be modified and printed.
 @author Mostafa Khedr
 */
public class BandOfTheHour {
    private static final Scanner keyboard = new Scanner(System.in);
    /**
     The final values and 2D array must be initialized
     */
    private static final int MAX_ROWS = 10;
    private static final int MAX_POSITIONS = 8;
    private static final double MAX_WEIGHT = 200.0;
    private static final double MIN_WEIGHT = 45.0;
    private static double[][] assignment;

    /**
     The main method introduces the program and accepts the nuber of rows for assignment and number of positions per row through input. After creating the 2D assignment array with the given
     values, the main method runs through a while loop displaying menu options and calling their respective methods if they are selected through input.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Band of the Hour");
        System.out.println("-------------------------------");
        int rows = getRows();
        keyboard.nextLine();
        assignment = new double[rows][];
        for(int i = 0; i < assignment.length; i++){
            char rowLetter = (char)('A' + i);
            System.out.print("Please enter number of positions in row " + rowLetter);
            int numPositions = keyboard.nextInt();
            keyboard.nextLine();
            if (numPositions < 1 || numPositions > MAX_POSITIONS) {
                System.out.print("ERROR: Out of range, try again            : ");
                numPositions = keyboard.nextInt();
                keyboard.nextLine();
            }
            assignment[i] = new double[numPositions];
        }
        char choice = 'A';
        while(choice != 'X') {
            System.out.print("(A)dd, (R)emove, (P)rint,          e(X)it : ");
            choice = keyboard.nextLine().toUpperCase().charAt(0);
            if(choice != 'A' && choice != 'R' && choice != 'P' && choice != 'X'){
                System.out.print("ERROR: Out of range, try again            : ");
                choice = keyboard.nextLine().toUpperCase().charAt(0);
            }
            if (choice == 'A'){
                addMusician();
                keyboard.nextLine();
            }
            else if (choice == 'R'){
                removeMusician();
                keyboard.nextLine();
            }
            else if (choice == 'P'){
                printAssignment();
                keyboard.nextLine();
            }



        } // end of the menu while loop
        System.out.println("EXIT");





    } // end of the main method

    /**
     * The getRows method is called in the beginning of the program to prompt the user to enter the number of rows for assignment.
     * @return - int value for number of rows.
     */
    private static int getRows() {
        System.out.print("Please enter number of rows               : ");
        int rows = keyboard.nextInt();
        if (rows < 1 || rows > MAX_ROWS) {
            System.out.print("ERROR: out of range, try again            : ");
            rows = keyboard.nextInt();
        }
        return rows;
    } // end of the getRows method

    /**
     * The addMusician method prompts the user to enter the row letter and position number of the musician they want to add, and changes that respective value in the array to
     * the double weight value also inputted by the user, which is input validated for weight limit constrictions.
     * If the value in that spot already has a value above 0.0 then a musician has already been assigned, it will print the error message and exit the method.
     */
    private static void addMusician(){
        char rowLetter;
        int position;
        double weight;
        System.out.print("Please enter row letter                   : ");
        rowLetter = keyboard.nextLine().toUpperCase().charAt(0);
        if (rowLetter < 'A' || rowLetter > 'A' + assignment.length) {
            System.out.print("ERROR: Out of range, try again            : ");
            rowLetter = keyboard.nextLine().toUpperCase().charAt(0);
        }
        int rowIndex = rowLetter - 'A';
        System.out.print("Please enter position number (1 to " + assignment[rowIndex].length + ")     : ");
        position = keyboard.nextInt();
        if (position < 1 || position > assignment[rowIndex].length){
            System.out.print("ERROR: Out of range, try again            : ");
            position = keyboard.nextInt();
        }
        System.out.print("Please enter weight (45.0 to 200.0)       : ");
        weight = keyboard.nextDouble();
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            System.out.print("ERROR: Out of range, try again            : ");
            weight = keyboard.nextDouble();
        }
        if (assignment[rowIndex][position - 1] != 0){
            System.out.print("ERROR: There is already a musician there.");
            return;
        }
        double rowWeight = 0;
        for (int i = 0; i < assignment[rowIndex].length; i++){
            rowWeight += assignment[rowIndex][i];
        }
        if (rowWeight > assignment[rowIndex].length * 100){
            System.out.print("ERROR: That would exceed the average weight limit.");
            return;
        }
        assignment[rowIndex][position-1] = weight;
        System.out.println("****** Musician added.");

    } // end of the addMusician method

    /**
     * The removeMusician method prompts a user to enter the row letter and position number of the musician they want to remove and changes that respective value in the array to 0.0,
     * unless the value in that spot is already 0.0 in which chase it will print the error message and exit the method.
     */
    private static void removeMusician(){
        char rowLetter;
        int position;
        System.out.print("Please enter row letter                   : ");
        rowLetter = keyboard.nextLine().toUpperCase().charAt(0);
        if (rowLetter < 'A' || rowLetter > 'A' + assignment.length) {
            System.out.print("ERROR: Out of range, try again            : ");
            rowLetter = keyboard.nextLine().toUpperCase().charAt(0);
        }
        int rowIndex = rowLetter - 'A';
        System.out.print("Please enter position number (1 to " + assignment[rowIndex].length + ")     : ");
        position = keyboard.nextInt();
        if (position < 1 || position > assignment[rowIndex].length){
            System.out.print("ERROR: Out of range, try again            : ");
            position = keyboard.nextInt();
        }
        if (assignment[rowIndex][position - 1] == 0){
            System.out.print("ERROR: That position is vacant.");
            return;
        }
        assignment[rowIndex][position-1] = 0.0;
        System.out.println("****** Musician removed.");

    } // end of the removeMusician method

    /**
     * The printAssignment method uses a nested for loop to display the 2D array of assignments, with the total and average weight displayed in brackets for each row.
     */
    private static void printAssignment(){
        for(int i = 0; i < assignment.length; i++){
            System.out.println();
            char rowLetter = (char)('A' + i);
            System.out.print(rowLetter + ":   ");
            for(int j = 0; j < assignment[i].length; j++){
                System.out.print(assignment[i][j] + "   ");
            }
            /**
             * Total weight calculated by iterating through the row and adding each value to a previously declared double totalWeight.
             */
            double totalWeight = 0.0;
            for (int j = 0; j < assignment[i].length; j++){
                totalWeight += assignment[i][j];
            }
            double averageWeight = totalWeight / assignment[i].length;
            System.out.println("[    " + totalWeight + ",     " + averageWeight + "]");
        }

    } // end of the printAssignment method






} // end of the BandOfTheHour class
