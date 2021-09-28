public class Characters extends Individual {
    protected int numLifes;
    protected Object has;

    public void move(float coordX, float coordY){
        this.coordX = coordX;
        this.coordY = coordY;

    }

    public void has(Object has){
        this.has = has;
    }

    public void use (){
    }

    public Object leave(){
        Object Object = has;
        has = null;
        return Object;
    }

    public void pifPaf(){}
}
