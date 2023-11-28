import java.util.HashMap;//fait partie du Framework Collection; 
//HashMap ≈ « tableau associatif » qui contient un ensemble d’associations clé→valeur
import java.util.Stack;//fait partie du Framework Collection
import java.io.IOException;//si j'essaye de lire un dossier qui n'existe pas ou ne marche pas,
//indique au compilateur qu'une entrée ou une sortie non valide s'est produite.
import java.io.File;//classe qui permet de lister les fichiers d'un répertoir, savoir si il est présent ou pas
import java.util.Scanner; //classe importé permet de parcourir les fichiers
/**
 * Classe principale du jeu
 *
 * @author Hugo DAUVERGNE
 * @version 24/05/22
 */
public class GameEngine
{
    // ## Attributs ##
    private Parser aParser;
    private UserInterface aGui;
    private Stack <Room> aLastRoom;
    private Player aPlayer;
    
    
    // ## Constructeur(s) ##
    /**
     * constructeur de Game
     */
    public GameEngine () //appeler la méthode createRooms déjà écrite
    {
        this.aPlayer = new Player("Doctor Patrick",0);
        this.createRooms();
        this.aParser = new Parser(); //création objet Parser
        this.createItems();
        this.aLastRoom = new Stack <Room>();
    }//Game
    
    
    // ## Modificateur ##
    /**
     * Procédure qui modifie l'interface graphique
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
    /**
     * Permet d'afficher le message de bienvenue lorsqu'on lance le jeu
     */
    private void printWelcome()
    {
        aGui.println("Welcome to Doctor under pressure !");
        aGui.println("Doctor under pressure is a new adventure game.");
        aGui.println("Type 'help' if you need help.");
        aGui.println("\n");
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }//printWelcome
    
    /**
     * Initialise les rooms, sorties et la piece courante
     */
    private void createRooms() 
    {
        // ## déclaration des rooms ##
        Room vReception = new Room("in the reception hospital", "Images/reception.gif","");
        Room vInstrument = new Room("in the instrument room","Images/instrument.gif","");
        Room vWc = new Room("in the WC","Images/wc.gif","");
        Room vLocker = new Room("in the changing room","Images/vestiaire.gif","clothes");
        Room vOperation = new Room("in the operation room","Images/bloc.gif","");
        
        // ## déclaration des sorties ##
        vReception.setExits( "south", vLocker ); //appele de fonction dans la classe Room
        vInstrument.setExits( "north", vOperation );
        vWc.setExits( "east", vLocker );
        vLocker.setExits( "north", vReception );
        vLocker.setExits("west", vWc);
        vLocker.setExits("down", vOperation);
        vOperation.setExits( "south", vInstrument);
        vOperation.setExits("up",vLocker);
        
        // ## Objets ##
        Item vId = new Item ("\nIDBadge","Needed to avoid any infection disease contracted by the patient", 1);
        vReception.addItem("IDBadge",vId);
        Item vKnife = new Item ("\nDiathermyKnife","Needed to avoid any infection disease contracted by the patient", 1);
        vInstrument.addItem("DiathermyKnife",vKnife);
        Item vKnife2 = new Item ("\nBistoury","Needed to avoid any infection disease contracted by the patient", 1);
        vInstrument.addItem("Bistoury",vKnife2);
        //initialise le lieu courant
        this.aPlayer.setCurrentRoom(vReception); //aCurrentRoom stocke vReception qui stocke tout les lieux,
                                                //car tous liés. De plus lié à la classe de l'objet courant Game.
    }//createRooms
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
    
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else{
                this.endGame();
            }
        }
        else if (vCommandWord.equals("back")){   
            back();
        }
        else if (vCommandWord.equals("test")) {
            this.test(vCommand); 
        }
        else if (vCommandWord.equals("take")) {
            this.take(vCommand);
        }
        else if (vCommandWord.equals("drop"))  {
            this.drop(vCommand);
        }
        else if (vCommandWord.equals("item")) {
            this.item(vCommand) ;
        }
        else if (vCommandWord.equals("look")){
            this.look(vCommand);
        }
    }//interpretCommand
        
    /**
     * Permet d'afficher le message ci dessous si le joueur inscrit help
     */
    private void printHelp()
    {
       this.aGui.println("You are lost. You are alone.");
       this.aGui.println("You wander around at the hospital."+ "\n");
       this.aGui.println("Your command words are: "+ this.aParser.getCommandString() );
    }//printHelp
    
    /**
     * Montre les portes de sorties
     */
    private void look(final Command pCommand)
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getExitString());
    }
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom( final Command pCommand ) 
    {
        if ( ! pCommand.hasSecondWord() ) {
            // if there is no second word, we don't know where to go...
            this.aGui.println( "Go where?" );
            return;
        }
        
        
        String vDirection = pCommand.getSecondWord();
        this.aGui.println("test :" + vDirection);
        // Try to leave current room.
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit( vDirection );
        
        

        if ( vNextRoom == null )
             this.aGui.println( "There is no door!" );
        else {
            this.aLastRoom.push(this.aPlayer.getCurrentRoom()); //push(): ajoute un élément dans la pile
            this.aPlayer.setCurrentRoom(vNextRoom);//getCurrentRoom(); //= vNextRoom;
            this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
            if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
        }
    }//goRoom
    
    
    /**
     * Permet la création d'un item dans une salle
     */
    public void createItems (){
        Item vClothes = new Item ("surgical gown","Needed to avoid any infection disease contracted by the patient", 1);
    }
    
    /**
    * Procédure Back, permettant de retourner dans la Room précédente.
    */
    private void back()
    {
       if(this.aLastRoom.empty()){  //empty(): boolean true si la pile est vide
           this.aGui.println( "You are in the first room.");
        }
       else{
           this.aPlayer.setCurrentRoom(this.aLastRoom.pop()); //supprime dernier élément dans la pile et le retourne
           this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription( ) );
          if (this.aPlayer.getCurrentRoom().getImageName( ) != null )
            {
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName()); 
          }  
        }
    }//back
    
    /**
     * Message de fin du jeu + Fermeture de la fenetre de jeu
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
    /**
     * Procédure de la commande test qui actionne les commandes d'un .txt dans le dossier racine du jeu. 
     * Il suffit d'écrire : test + (nom du .txt).
     */
    private void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord()) 
        {
            aGui.println("Which files do you want to test ?");
        }
        try {
            File vFileTest = new File(pCommand.getSecondWord()+".txt");
            Scanner vScan = new Scanner (vFileTest);
            while (vScan.hasNextLine()) //hasNextLine(): boolean regarde si on passe a la ligne suivante ou pas
            {
                interpretCommand(vScan.nextLine());//nextLine(): ligne suivante
            }
            vScan.close();
        }
        catch (final java.io.FileNotFoundException pE){
           aGui.println("File not found.");
        }
    }//test
    
    /**
     * Procédure take 
     */
    private void take(final Command pCommand )
    {
        if(!pCommand.hasSecondWord()){
            this.aGui.println( "Take what ?");
            return;
        }
        Item vItem = this.aPlayer.getCurrentRoom().getItem(pCommand.getSecondWord()); // création de la variable vItem
        if (vItem == null){
            this.aGui.println("This item is not here. ");
        }
        else if(this.aPlayer.canCarry(vItem)==false)
        {
           this.aGui.println("WARNING!! It's too heavy"); 
        }
        else{
            this.aPlayer.takeItem(pCommand.getSecondWord(), vItem); 
            this.aPlayer.getCurrentRoom().removeItem(pCommand.getSecondWord( )); //retire l'item de la Room
            this.aGui.println("You take the item "+pCommand.getSecondWord( ));
        }
    }//take
    /**
     * Procédure drop
     */
    private void drop(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Drop what ?");
            return;
        }
        String vItem = pCommand.getSecondWord();
        Item vToDrop = this.aPlayer.getItem(vItem);
        if(vToDrop == null) {
            this.aGui.println("I don't have it !");
        }
        else{
            this.aPlayer.getCurrentRoom().addItem(vItem, vToDrop);
            this.aPlayer.dropItem(vItem);
            this.aGui.println("I have drop it !");
        }//drop
    }//drop
    
    /**
     * Affiche l'inventaire
     */
    private void item(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            this.aGui.println("Item cannot take parameter.");
            return;
        }
        String Inventory = this.aPlayer.getInventoryString();
        this.aGui.println(Inventory);
    }
}
