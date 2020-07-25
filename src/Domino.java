/**This class represents a Domino as described in the post correspondence problem.  A
 * Domino in this program is defined as having a top string and a bottom string as in the
 * problem.  I also assigned each Domino a name (D1, D2, D3, etc) since the program should be
 * able to return the exact sequence of dominos used.
 * 
 * @author Reed Schick
 * @version 1.0
 */
public class Domino{
    protected String name; //Name of Domino
    protected String top; //Top String
    protected String bot; //Bottom String

    /**Creates a New Domino.
     * 
     * @param name Name of Domino
     * @param top top string
     * @param bot bottom string
     */
    public Domino(String name, String top, String bot){
        this.name = name;
        this.top = top;
        this.bot = bot;
    }

    /**Method for retrieving a domino's top string
     * 
     * @return top
     */
    public String getTop(){
        return top;
    }

    /**Method for retrieving a domino's bottom string
     * 
     * @return bot
     */
    public String getBot(){
        return bot;
    }

    /**Method for retrieving a domino's name
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
}