package sample.Organization;

import java.io.Serializable;
import java.util.Objects;
public class AlternativesForMarks extends Generalization implements Serializable {
    static final long serialVersionUID = 1L; //assign a long value


    private int id;
    private String name;
    private String country;
    private double cost;
    private String typeOf;
    //marks for pair compare;
    private String firstEx; // 0  1  -
   /* private String secondEx;
    private String thirdEx;*/




    public AlternativesForMarks() {
    }

    public int getId() {

        return id;
    }
public AlternativesForMarks(int id, String NameGen)
{
   super(id,NameGen);
}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }

   /* public String getSecondEx() {
        return secondEx;
    }

    public void setSecondEx(String secondEx) {
        this.secondEx = secondEx;
    }

    public String getThirdEx() {
        return thirdEx;
    }

    public void setThirdEx(String thirdEx) {
        this.thirdEx = thirdEx;
    }
*/

    public String getFirstEx() {
        return firstEx;
    }

    public void setFirstEx(String firstEx) {
        this.firstEx = firstEx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlternativesForMarks alternative = (AlternativesForMarks) o;
        return id == alternative.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
