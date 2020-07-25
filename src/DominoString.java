/**This class is an extension of the Domino class, with some extra functionality.
 * A domino string can be thought of a sequence of dominos as is created in the post correspondence
 * problem.  The name becomes a comma seperated list of every domino that is appended to
 * the Domino String.  In addition, top and bottom contain the appended tops and bottoms of
 * every domino used in the sequence, in order of the name.
 * 
 * @author Reed Schick
 * @version 1.0
 */
public class DominoString extends Domino{

    /**Constructor for a new domino string.  Simply calls the super class.
     * 
     * @param name Initial name of the domino string
     * @param top Initial top string of the domino string
     * @param bot Initial bottom string of the domino string
     */
    public DominoString(String name, String top, String bot){
        super(name, top, bot);
    }

    /**This function checks if the domino string is an answer as defined by the post correspondence
     * problem.  It makes this decision based on the length of the domino, as the main program will
     * only append new dominos if it is sure that they are valid additions.  This could have been
     * encapsulated better so that the "returnAppended" function did this check within the function and only
     * created a new valid domino if it was valid.
     * 
     * @return true if the dominostring is valid (same length), false otherwise
     */
    public boolean isAnswer(){
        if (top.length() == bot.length()){
            return true;
        }
        return false;
    }

    /**This function returns a new domino string which is created by adding the given domino
     * to the end of the DominoString called upon.  The main program will only call this function if it has
     * already checked the "canAppend" function, but this could have been encapsulated better
     * by placing the call to "canAppend" directly within the returnAppended functon.
     * 
     * @param dom the domino to be added
     * @return a new DominoString created by appending the given domino to the end of the DominoString that was called upon
     */
    public DominoString returnAppended(Domino dom){
        String name; //the new DominoString's name

        //this is an option so that an initially empty DominoString will not recieve
        //a comma before the first Domino in it's list
        if(this.name == null || this.name.equals("")){
            name = dom.getName();
        }

        //The new name is a combo of the name of this DominoString and that of the given
        //Domino, seperated by a comma.
        else{
            name = this.name + "," + dom.getName();
        }

        //Combine tops and bottoms.
        String top = this.top + dom.getTop(); //the new DominoString's top
        String bot = this.bot + dom.getBot(); //The new DominoString's bottom
        return new DominoString(name, top, bot);
    }

    /**this function takes a domino and creates the "top" and "bottom" strings that would be
     * produced if it was appended to the DominoString called upon.  It then determines which string
     * is shorter, as a valid DominoString need only have the shorter string match the longer string
     * in the characters that number it's length.  If the suggested DominoString is valid, the function
     * will return true
     * 
     * @param toAdd a suggested domino to add to the DominoString
     * @return true if the domino is a valid suggestion, false otherwise
     */
    public boolean canAppend(Domino toAdd){
        String checkTop = top + toAdd.getTop(); //resulting top string
        String checkBot = bot + toAdd.getBot(); //resulting bottom string
        int topLen = checkTop.length();
        int botLen = checkBot.length();

        //bottom is shorter
        if(topLen > botLen){
            if(checkBot.equals(checkTop.substring(0, botLen))){
                return true;
            }
        }

        //top is shorter
        if(botLen > topLen){
            if(checkTop.equals(checkBot.substring(0, topLen))){
                return true;
            }
        }

        //string lengths are equal
        else{
            if(checkTop.equals(checkBot)){
                return true;
            }
        }
        return false;
    }

    /**A simple toString method that puts the name of the DominoString within brackets,
     * to match the desired output for each state that the professor wanted
     * 
     * @return a bracketed, comma seperated list of each Domino used in the DominoString, in order
     */
    public String toString(){
        return "[" + this.name + "] " + this.top + " " + this.bot;
    }
}