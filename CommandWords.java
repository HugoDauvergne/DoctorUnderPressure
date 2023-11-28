/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Hugo DAUVERGNE
 * @version 2008.03.30 + 2019.09.25 + 2022.05.18
 */
public class CommandWords
{
    // ## Attribut ##
    
    //un tableau constant qui conserve les commandes valides
    //static = attribut partagé par toutes les instances de la classe
    private static String validCommands[] = {"go", "quit", "help", "look","back","test","take", "drop", "item"} ; 
    
    
    // ## Constructeur ##
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.validCommands = new String[9];
        this.validCommands[0] = "go";
        this.validCommands[1] = "help";
        this.validCommands[2] = "quit";
        this.validCommands[3] = "look";
        //this.validCommands[4] = "treat";
        this.validCommands[4] = "back";
        this.validCommands[5] = "test";
        this.validCommands[6] = "take";
        this.validCommands[7] = "drop";
        this.validCommands[8] = "item";
    } // CommandWords()
    
    
    // ## Accesseur(s) ##
    /**
     * @return Affiche toutes les commandes valides 
     */
    
    public String getCommandList()
    {
        /**String vS = "";
        for (String command : validCommands){
            System.out.print (command + " ");
        }
        return vS;**/
        StringBuilder commands = new StringBuilder();
        for (int i = 0; i<validCommands.length;i++){
            commands.append(validCommands[i]+" ");
        }
        return commands.toString();
    }
    
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     * @param pString
     */
    public boolean isCommand( final String pString )
    {
        for ( int i=0; i<this.validCommands.length; i++ ) {
            if ( this.validCommands[i].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand()
    /**
     * Affichage de toutes les commandes du jeu
     */
    public void showAll()
    {
        for (String command : validCommands){
            System.out.print(command +" ");
        }
        System.out.println();
    }
    
}// CommandWords