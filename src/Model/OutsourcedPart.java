package Model;

/**
 *
 * @author Scott.Morales
 */
public class OutsourcedPart extends Part{
    
    private String companyName;
    
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
        
    public String getCompanyName(){
        return this.companyName;
    }
}