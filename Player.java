import java.util.Stack;
import java.util.HashMap;
import java.util.List;

/**
 * Caractéristiques du personnage
 *
 * @author Hugo DAUVERGNE
 * @version 20/05/22
 */
public class Player
{
    // ## Attributs ##
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aLastRoom;
    private HashMap<String, Item> aInventary;
    private ItemList aList;
    private double aMaxWeight;//poids à ne pas dépasser
    private double aWeight; //poids courant du personnage
    
    // ## Constructeur ##
    /**
     * Constructeur naturel de Player
     */
    public Player(final String pName, final double pMaxWeight)
    {
        this.aName = pName;
        this.aCurrentRoom = null;
        this.aLastRoom = new Stack();
        this.aList = new ItemList();
        this.aMaxWeight = 2;
        this.aWeight = 0;
    }
    
    // ## Accesseur ##
    /**
     * Déclaration d'un accesseur Name public de classe String
     */
    public String getName(){
        return this.aName;
    }
    /**
     * Déclaration d'un accesseur CurrentRoom public de classe Room
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }
    /**
     * 
     */
    public Room getLastRoom(){
        return this.aLastRoom.pop();
    }
    /**
     * Renvoi l'item correspondant à la string
     * @param pItem String qui correspond à la description de l'objet
     * @return Item qui correspond à la string
     */
    public Item getItem(final String pItem){
        return this.aList.getItem(pItem);
    }
    public String getInventoryString()
    {
        return this.aList.getInventoryString();
    }
    
    // ## Modificateur ##
    public void setName(final String pName){
        this.aName=pName;
    }
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        this.aCurrentRoom = pCurrentRoom;
    }
    public void setLastRoom(final Room pLastRoom)
    {
        this.aLastRoom.push(pLastRoom);
    }
    
    
    public boolean lastRoomsIsEmpty()
    {
        return this.aLastRoom.empty();
    }
    
     /**
     * Permet de prendre des objets disponible dans une piece 
     * @param pStringItem, pItem
     */
    public void takeItem(final String pStringItem, final Item pItem)
    {
        this.aList.takeItem(pStringItem, pItem);
    }
    /**
     * Permet de lacher des objets qui sont dans l'inventaire
     * @param pStringItem
     */
    public void dropItem(final String pStringItem)
    {
        this.aList.dropItem(pStringItem);
    }
    
    
    /**
     * Modifie le poids courant du personnage aWeight 
     * pour qu'il prenne valeur du poids total
     */
    public double totalWeight()
    {
        return this.aWeight=this.aList.getTotalWeight();
    }
    
    /**
     * Peut ou pas porter l'item sur lui
     */
    public boolean canCarry(final Item vItem)
    {
        return totalWeight()+vItem.getWeight()<= this.aMaxWeight;
    }
}//Player
