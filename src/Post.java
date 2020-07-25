import java.io.*;
import java.util.*;
/**This Program is meant to solve the post correspondence problem to the best of it's
 * ability.  A user can provide a file that describes how may states to check and all dominos to use
 * and the program will try to find a solution using a breadth first search of the state space.  It
 * will either return an answer, state that there is no answer if no states are left, or state that
 * no answer could be found in the confines of it's search if none of the states it produces before
 * reaching it's limit are answers.
 * 
 * @author Reed Schick
 * @version 1.0
 */
public class Post{

    public static ArrayList<Domino> myDoms; //a list to hold all of the given Dominos
    public static int maxStates; //the maximum number of states that the program is to produce

    /**main method that calls the input reading and calculation functions
     * 
     * @param args command line arguments
     */
    public static void main(String[] args){
        readInput(args);
        calculateStates();
    }

    /**Basic file reading, that checks for a command line argument and if the file exists or not.
     * the program expects the correctly formatted input, and does not check if the input provided is
     * bad or not
     * 
     * @param args command line argument, hopefully specifying the file to read
     */
    public static void readInput(String[] args){

        //program expects a file name
        if(args.length == 0){
            System.out.println("The Program expects a file as input.\nTerminating...\n");
            System.exit(0);
        }

        File info = new File(args[0]); //file to be read, whose location is defined by the command line arguments
        Scanner scan; //scanner for file reading

        //This try catch block will catch an exception thrown if the file path passed in is
        //invalid, and will print an error message and terminate in this case.
        try{
            scan = new Scanner(info);

            myDoms = new ArrayList<Domino>();
            maxStates = Integer.parseInt(scan.next());
            String top; //holds incoming Domino's top String
            String bot; //holds incoming Domino's bottom String
            int cur = 1; //indexes the Domino coming in

            //read in dominos one by one, assigning a name to each one based on the iteration number
            while(scan.hasNext()){
                scan.next();
                top = scan.next();
                bot = scan.next();
                myDoms.add(new Domino("D" + cur, top, bot));
                cur ++;
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("The File " + args[0] + " could not be found.\nTerminating...\n");
            System.exit(0);
        }
    }

    /**This method calculates states, and terminates if an answer is found, if there is no answer, or
     * if the maximum amount of states as defined by the user is reached.  It makes use of a hashtable
     * so that indentical states are not produced.  It performs this search through a depth first
     * search.
     */
    public static void calculateStates(){
        Queue<DominoString> states = new Queue<DominoString>(); //Queue to hold states as they are created
        states.enqueue(new DominoString(null, "", "")); //adds an "empty state" to the front of the Queue to start building upon
        DominoString curState; //will be used to hold the current state as it is dequeued each iteration
        Domino curDom; //will be used to hold the domino to be checked by curState as the program iterates through each domino for each state
        int statesProduced = 0; //keeps track of how many states have been produced
        Hashtable<String, String> premade = new Hashtable<String, String>(); //hashtable to store dominos for quick look up, so states aren't reproduced.

        //main loop.  This runs while the maximum number of states has not been produced and while there are states to check
        while(statesProduced < maxStates && !(states.isEmpty())){
            curState = states.dequeue(); //get the next state

            //iterates through all dominos to check if they can be added to the current state.
            //this loop will end early if the maximum number of states are produced
            for(int i = 0; i < myDoms.size() && statesProduced < maxStates; i ++){

                curDom = myDoms.get(i); //get the next domino

                //only complete logic if the combination is valid
                if(curState.canAppend(curDom)){
                    DominoString arrival = curState.returnAppended(curDom); //make a new DominoString

                    //check the hash table for "top-bottom", a unique string for each DominoString.
                    //if it already is in the table, the state is a replica and we do not count it/
                    if(premade.get(arrival.getTop() + "-" + arrival.getBot()) != null){
                        continue;
                    }

                    //print out the state.  if it is the answer, reprint it as such and terminate
                    System.out.println(arrival.toString());
                    if(arrival.isAnswer()){
                        System.out.println("Answer: " + arrival.toString());
                        System.exit(0);
                    }
                    states.enqueue(arrival); //add the new state to the queue
                    premade.put(arrival.getTop() + "-" + arrival.getBot(), arrival.getName()); //add the unique string to the hashtable
                    statesProduced ++; //keep track of how many states are produced
                }
            }
        }

        //max states were reached; may or may not be an answer
        if(statesProduced == maxStates){
            System.out.println("No Answer could be found within the confines of this search.");
        }

        //no more states to check, even though max has not been reached.  There is no answer
        else{
            System.out.println("No solutions exist.");
        }
    }
}