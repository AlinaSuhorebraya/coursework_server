package sample.Organization;

public class Generalization {
    protected int id;
    protected String NameGen;
    public Generalization(){};
    public Generalization(int id, String NameGen)
    {
        this.id=id;
        this.NameGen=NameGen;
    }
    public String getNameGen() {
        return NameGen ;
    }

    public void setNameGen(String name) {
        this.NameGen = name;
    }
    public int getIdGen()
    {return id;}
    public void setIdGen(int id)
    {this.id=id;}




}
