


public class CarroGasolina extends Veiculo implements Autonomia {

    //-------------------------------------------------------//  

    private double consumoPorKm;
    private double autonomiaMaxima;
    private double autonomiaAtual;

    //-------------------------------------------------------//  

    public CarroGasolina (String nova_matricula,
                          double velMedia,
                          double priceKM,
                          double classiF,
                          Localizacao nova_local,
                          double consumo,
                          double maxAuto,
                          double currentAuto,
                          String prop,
                          boolean estado) {

        super(nova_matricula, velMedia, priceKM, classiF, nova_local, prop, estado);

        this.consumoPorKm    = consumo;
        this.autonomiaMaxima = maxAuto;
        this.autonomiaAtual  = currentAuto;
    }

    public CarroGasolina (CarroGasolina carroGas) {

        super(carroGas);

        this.consumoPorKm    = carroGas.getConsumoPorKM();
        this.autonomiaMaxima = carroGas.getAutonomiaMaxima();
        this.autonomiaAtual  = carroGas.getAutonomiaAtual(); 
    }

    public CarroGasolina () {

        super();

        this.consumoPorKm    = 0;
        this.autonomiaMaxima = 0;
        this.autonomiaAtual  = 0;
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroGasolina g = (CarroGasolina) o;
     
        return(super.equals(g)                             && 
               this.consumoPorKm == g.getConsumoPorKM()    &&
               this.autonomiaMaxima == g.getAutonomiaMaxima() &&
               this.autonomiaAtual == g.getAutonomiaAtual());
    }

    public CarroGasolina clone () {

        return new CarroGasolina(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de Veiculo: Carro a gasolina.\n");
        s.append(super.toString());
        s.append("Cons./KM: " + this.consumoPorKm + "\n");
        s.append("Deposito (Max): " + this.autonomiaMaxima + "\n");
        s.append("Deposito (Atual) : " + this.autonomiaAtual + "\n");

        return s.toString();
    }


    //-------------------------------------------------------//  

    public double getConsumoPorKM () {

        return this.consumoPorKm;
    }
   
    public double getAutonomiaMaxima () {

        return this.autonomiaMaxima;
    }

    public double getAutonomiaAtual () {

        return this.autonomiaAtual;
    }
    
    public void setAutonomiaAtual(double atual){
        
        this.autonomiaMaxima = atual;
    }

    public double percentagemAutonomia () {
     
        return ((this.autonomiaAtual/this.autonomiaMaxima) * 100);
    }

    public boolean veiculoDisponivelAluguer(){
    
        return(this.percentagemAutonomia() >= 10);
    }
    
    //-------------------------------------------------------//  

    public void moverParaPosicao (double x, double y){
        
        Localizacao l = new Localizacao(x,y);
        
        double p = this.getAutonomiaAtual() - this.getLocalizacao().distancia(l);
        
        this.setAutonomiaAtual((p<0)?0:p);
        
        this.setLocalizacao(l);
    }

    public boolean temAutonomiaParaViagem (double distancia){
    
        return(this.getAutonomiaAtual() >= distancia);
    } 
}