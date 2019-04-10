import java.util.List;
import java.util.ArrayList;

public class Veiculo {

    //-------------------------------------------------------//  

    private String matricula;
    private float velMediaPorKM;
    private float precoPorKm;
    private float classificacao;
    private Localizacao local;
    
    //-------------------------------------------------------//  

    public Veiculo (String nova_matric,
                    float mediumVel, 
                    float precKM,
                    float classif,
                    Localizacao local_carro) {

        this.matricula       = nova_matric; 
        this.velMediaPorKM   = mediumVel;
        this.precoPorKm      = precKM;
        this.classificacao   = classif;
        this.local           = new Localizacao(local);
    }
    
    public Veiculo (Veiculo novo) {

        this.matricula       = novo.getMatricula();
        this.velMediaPorKM   = novo.getVelMediaPorKM();
        this.precoPorKm      = novo.getPrecoPorKM();
        this.classificacao   = novo.getClassificacao();
        this.local           = novo.getLocalizacao();
    }

    public Veiculo () {

        this.matricula       = "00-00-00";
        this.velMediaPorKM   = 0;
        this.precoPorKm      = 0;
        this.classificacao   = 0;
        this.local           = new Localizacao();
    }
  
    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Veiculo v = (Veiculo) o;
     
        return(this.matricula.equals(v.getMatricula())    &&
               this.velMediaPorKM == v.getVelMediaPorKM() &&
               this.precoPorKm == v.getPrecoPorKM()       &&
               this.classificacao == v.getClassificacao() &&
               this.local.equals(v.getLocalizacao()));
    }

    public Veiculo clone () {

        return new Veiculo(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("Matrícula: " + this.matricula + "\n");
        s.append("Vel_Med/KM: " + this.velMediaPorKM + "\n");
        s.append("Preco/KM: " + this.precoPorKm + "\n");
        s.append("Class.: " + this.classificacao + "\n");
        s.append(this.local.toString());

        return s.toString();
    }
    
    //-------------------------------------------------------//  

    public String getMatricula () {

        return this.matricula;
    }

    public float getVelMediaPorKM () {

        return this.velMediaPorKM; 
    }

    public float getPrecoPorKM () {

        return this.precoPorKm;
    }

    public float getClassificacao () {

        return this.classificacao;
    }

    public Localizacao getLocalizacao () {

        return this.local;
    }

    //-------------------------------------------------------//  
    
    public void moverParaPosicao (int x, int y){
        


    }
}
