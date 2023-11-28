import java.util.HashMap;//Collection Framework, HashMap ≈ « tableau associatif » qui contient un ensemble d’associations clé→valeur
/**
 * The Item class.
 *  @author Hugo DAUVERGNE
 * @version 24/05/22
 */
public class Item
{
    // ## Attributs ##
    private String aName;
    private String aDescription;
    private double aWeight;
    
    
    // ## Constructeur ##
    /**
     * The class' constructor
     * @param pN The name of the Item.
     * @param pD The description of the Item.
     * @param pW The weight of the Item.
     */
    public Item(final String pN, final String pD, final double pW){
        this.aName = pN;
        this.aDescription = pD;
        this.aWeight = pW;
    }
    
    
    // ## Accesseur ##
    /**
     * @return The Item's name.
     */
    public String getName(){
        return this.aName;
    }
    /**
     * @return The Item's description.
     */
    public String getDescription(){
        return this.aDescription;
    }
    /**
     * @return The Item's weight.
     */
    public double getWeight(){
        return this.aWeight;
    }
    
    
    // ## Modificateur ##
    /**
     * --attribut description devient de type String
     */
    public void setDescriptionItem( final String pDescription ){ 
        this.aDescription=pDescription ;
    }
    /**
     * --attribut setName devient de type String
     */
    public void setName( final String pName){
        this.aName=pName;
    }
    /**
     * --attribut setWeight devient de type String
     */
    public void setWeight( final double pWeight){
        this.aWeight=pWeight ; 
    }
    
    
    /**
     * @return The Item's name next to its weight in parenthesis.
     */
    @Override //permet de surcharger la méthide la classe mère
    public String toString(){
        return this.aName + "(" + this.aWeight + "kg)";
    }
}

