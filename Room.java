import java.util.HashMap;//Collection Framework, HashMap ≈ « tableau associatif » qui contient un ensemble d’associations clé→valeur
import java.util.Set;//Collection du Framework, j'utilise des classes appelant des Sets
/**
 * Classe de la Room du projet Doctor Under Pressure
 * @author Hugo DAUVERGNE
 * @version 18/05/22
 */  
public class Room
{
    // ### Attributs ###
    private String  aDescription;
    private HashMap <String, Room> aExits;
    private String aImageName;
    private HashMap <String, Item> aItem;
    
    
    // ## Constructeur(s) ##
     /**
     * Créer une room en string et une HashMap pour toutes les sorties
     * @param pDescription String est la description de la room
     */
   // Constructeur naturel
   public Room( final String pDescription, final String pImage, final String pNom)
  {
      this.aDescription = pDescription;
      this.aExits = new HashMap<String,Room>();
      this.aImageName = pImage;
      this.aItem = new HashMap <String,Item>();
  }//Room
  
  
  // ## Accesseurs (get) ##
    /**
     * Renvoi les descriptions selon la direction passée en parametre
     * @return Door correspond à la direction
     */
  public String  getDescription() 
  { 
      return this.aDescription; 
    }//getDescription  
  /**
     * Retourne les String sorties
     * par exemple "Exits: north est".
     * @return String des sorties de la room
     */
  public String getExitString()
  {
      String returnString="Exits: ";
      Set <String> keys=this.aExits.keySet();
      for(String Exit : keys)
      {
          returnString+=' '+Exit;
      }//remplace ce qui a été écrit après
      return returnString;
  }//getExitString
  public Item getItem(final String pItem)
  {
        return this.aItem.get(pItem);
  }
  /**
     * Retourne la sortie de la Room
     * @return Retourne la salle après avoir choisit une direction
     * @param pDirection Direction vers l'endroit ou l'on veut se diriger
     */
  public Room getExit(final String pDirection)
  {
      return this.aExits.get(pDirection);
  }//getExit
  /**
   * Renvoie une description détaillée de cette pièce sous la forme :
   *    Vous êtes dans le bloc opératoir.
   *    Sorties : en haut sud
   *    @return Une description de la pièce, avec les sorties
   */
  public String getLongDescription()
  {
      StringBuilder sb = new StringBuilder();
      sb.append("You are "+ aDescription +".\n"+ getExitString());
      if (!this.aItem.isEmpty()) {
        for(String name : this.aItem.keySet()){
            sb.append(this.aItem.get(name));
        }
    }
    else{
        sb.append(".\n").append("There is no items in this room");
    }
    return sb.toString();
  }
  /**
   * @return aImageName
   */
  public String getImageName()
     {
        return this.aImageName;
     }
  
     
  // ## Modificateur ##
  /**
     * Change la sortie de la Room
     * @param pDirection Donne la direction prise
     * @param pNextRoom donne les directions disponibles
     */
  public void setExits(final String pDirection, final Room pNextRoom)
      {
      this.aExits.put(pDirection,pNextRoom);
  }//setExits
  
  
     
  /**
   * Procédure permettant d'ajouter un item dans une room
   */
  public void addItem(final String pNomItem, final Item pItem )
    {
    this.aItem.put(pNomItem, pItem);
    }

  
  /**
   * Procédure removeItem permettant de retirer un item d'une room
   */
  public void removeItem(final String pNomItem)
    {   
    this.aItem.remove(pNomItem);
    }   
     
  
} // Room
