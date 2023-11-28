import java.util.HashMap;//Collection Framework, HashMap ≈ « tableau associatif » qui contient un ensemble d’associations clé→valeur
import java.util.Set;//Collection du Framework, j'utilise des classes appelant des Sets
/**
 * Classe qui s'occupe de l'inventaire des Rooms et du Player.
 * @author Hugo DAUVERGNE
 * @version 22/05/22
 */
public class ItemList
{
    // ## Attributs ##
    private HashMap<String, Item> aInventary; //HashMap ≈ « tableau associatif » qui contient un ensemble d’associations clé→valeur, 
                                            //ici dans le cas présent, associe toute les chaines de caractère et toutes ces caractéristiques
    
    // ## Constructeur ##
    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        this.aInventary = new HashMap<>();
    }
    
    
    // ## Accesseurs ##
    /**
     * Renvoi l'item correspondant à la string
     * @param pItem String qui correspond à la description de l'objet
     * @return Item qui correspond à la string
     */
    public Item getItem(final String pItem){
        return this.aInventary.get(pItem);
    }
    /**
     * Donne le poids total du personnage
     * @return Weight qui correspond au poids
     */
    public double getTotalWeight()
    {
        double vWeight = 0;
        Set <String> vKeys = this.aInventary.keySet(); //Utilisation de la classe Set qui a été importé
        //keySet(): retourne l'ensemble des clées
        for(String vItem: vKeys)
        {
            vWeight+=getItem(vItem).getWeight();
        }
        return vWeight;
    }
    /**
     * Fonction getItemString de type String
     * @return Renvoi toutes les clées auxquels les items sont liées
     */
    public String getItemString(){
        String vReturnString = "";
        Set<String> vKeys = this.aInventary.keySet(); //Utilisation de la classe Set qui a été importé
        //keySet(): retourne l'ensemble des clées
        for(String vItem : vKeys)
        {
            vReturnString += " a "+vItem+"\n";
        }
        return vReturnString;
    }
    /**
     * Méthode qui retourne une description détaillé de l'inventaire avec nom et poids
     * @return les items présent dans l'inventaire
     */
    public String getInventoryString() 
    {
        StringBuilder returnString = new StringBuilder( "You've got " );
        if (this.aInventary.size()==0)
        {
            return "Empty inventory";
        }
        for (Item item : this.aInventary.values())
        {
            returnString.append(item);//rajoute une String a la fin de ma String
            returnString.append(" , ");
        }
        return returnString.toString();
    }//getInventoryString
    
    
    
    
    /**
     * Procédure takeItem qui a partir de la HashMap aInventary, 
     * associe une chaine de caractère et une caractéristique à takeItem
     */
    public void takeItem(final String pStringItem, final Item pItem){
        this.aInventary.put(pStringItem, pItem);
    }
    /**
     * Procédure dropItem qui a partir de la HashMap aInventary, 
     * associe une chaine de caractère à dropItem
     */
    public void dropItem(final String pStringItem){
        this.aInventary.remove(pStringItem);
    }
    
}//ItemList
