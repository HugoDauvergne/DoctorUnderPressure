/**
 * Classe Game du projet Doctor Under Pressure,
 * elle démarre le jeu.
 * 
 * @author Hugo DAUVERGNE
 * @version 18/05/22
 */
public class Game
{
    // ## Attributs ##
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map. Create the inerface and link to it.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }    
} // Game
