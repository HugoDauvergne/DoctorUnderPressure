/**
 * Cette classe contient des informations sur une commande émise par l'utilisateur.
 * Une commande se compose actuellement de deux chaînes : un mot de commande et un second
 * mot (par exemple, si la commande était "prendre la carte", alors les deux chaînes
 * sont évidemment "take" et "map").
 *
 * La façon dont cela est utilisé est : les commandes sont déjà vérifiées pour être valides
 * mots de commande. Si l'utilisateur a entré une commande invalide (un mot qui n'est pas
 * connu) alors le mot de commande est <null>.
 *
 * Si la commande n'avait qu'un seul mot, alors le deuxième mot est <null>.
 * 
 * @author Hugo DAUVERGNE
 * @version  18/05/22
 */ 
public class Command
{
   // ## Attributs ##
   private String aCommandWord;
   private String aSecondWord;
   
   
   // ## Constructeur ##
   /**
    * Constructeur naturel
    */
   public Command( final String pPremierMot, final String pDeuxiemeMot )
   {
      this.aCommandWord   = pPremierMot;
      this.aSecondWord   = pDeuxiemeMot;
   }//Command
   
   
   // ## Accesseur ##
   /**
    * retourne la string du premier mot écrit dans la boîte de dialogue
    * @return String CommandWord 
    */
   public String  getCommandWord() { 
       return this.aCommandWord; 
    } //getCommandWord
   /**
    * retourne la string du deuxième mot
    * @return String SecondWord
    */
   public String  getSecondWord() { 
       return this.aSecondWord; 
    } //getSecondWord

   // Fonction booléenne
   /**
    * Retourne un boolean si il y a un deuxieme mot
    * @return boolean 
    */
   public boolean hasSecondWord() //vérifie qu'un second mot a bien été tapé
   {
      return this.aSecondWord != null;
   } //hasSecondWord
   
   /**
    * Retourne un boolean True si le commandWord n'est pas connu et faux si c'est bon
    * @return boolean
    */
   public boolean isUnknown() //retourne vrai si le premier mot est null
   {
      return this.aCommandWord == null;
   } //isUnknown
}  // Command


