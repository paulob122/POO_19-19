import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

public class Proprietario extends AtorSistema
{

    //-------------------------------------------------------//  

    private Map<String,Veiculo> mapVeiculos;
    
    //-------------------------------------------------------//  

    public Proprietario(String email, 
                        String nome, 
                        String password, 
                        String morada, 
                        LocalDate data, 
                        int classificacao,
                        List<Aluguer> historico,
                        Map<String,Veiculo> veiculos) {
     
        super(email, nome, password, morada, data, classificacao, historico);
        this.mapVeiculos = veiculos.entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(e -> e.getKey(),
                                                                  e -> e.getValue(),
                                                                    (e1, e2) -> e2,
                                                                    HashMap::new));
    }
    
    public Proprietario (Proprietario umProprietario) {
    
        super(umProprietario);
        this.mapVeiculos = umProprietario.getMapVeiculos();
    }
    
    public Proprietario () {
        
        super();
        this.mapVeiculos = new HashMap<String, Veiculo>();
    }

    //-------------------------------------------------------//  

    public String toString (){
    
        StringBuilder s =  new StringBuilder();

        s.append("\n=> Tipo de ator de Sistema: Proprietario.");
        s.append(super.toString());
        s.append(this.getListaVeiculos().toString());

        return s.toString();
    }

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Proprietario p = (Proprietario) o;

        boolean veiculosIgual = true;
     
        return (super.equals(p) &&
                p.getListaVeiculos().equals(this.mapVeiculos));
    }
    
    public Proprietario clone() {

        return new Proprietario(this);
    }

    //-------------------------------------------------------//  
    
    public void adicionaVeiculo (Veiculo v) {

        
        if (!this.mapVeiculos.containsKey(v.getMatricula())) {

            this.mapVeiculos.put(v.getMatricula(),v.clone());
            this.sinalizarVeiculo(mapVeiculos.get(v.getMatricula()));
        }
    }

   
    public void alteraPrecoPorKm(Veiculo v, double preco){
        
        if(this.mapVeiculos.containsValue(v)){
            
            this.mapVeiculos.get(v.getMatricula()).setPrecoPorKm(preco);
        }
    }
    
   
    //-------------------------------------------------------//  

    public Map<String, Veiculo> getMapVeiculos() {

       return this.mapVeiculos.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
    }

    public List<Veiculo> getListaVeiculos() {

       return this.mapVeiculos.values()
                                   .stream()
                                   .map(Veiculo::clone)
                                   .collect(Collectors.toList());
    }
 
    public boolean existeVeiculo (Veiculo v) {

        return this.mapVeiculos.containsKey(v.getMatricula());
    }

    public void sinalizarVeiculo (Veiculo v){
    
        if(this.mapVeiculos.containsValue(v)){
            
            if(v.veiculoDisponivelAluguer()) this.mapVeiculos.get(v.getMatricula()).setDisponivel(true);
            else this.mapVeiculos.get(v.getMatricula()).setDisponivel(false);

        }
    }
    
    public void abastecer(Veiculo v){
    
        if(this.mapVeiculos.containsValue(v)){

            this.mapVeiculos.get(v.getMatricula()).setAutonomiaAtual(v.getAutonomiaMaxima());

        }
    }
    
    public Veiculo getVeiculoPelaMatricula (String matricula){
        
        return this.mapVeiculos.containsKey(matricula)?this.mapVeiculos.get(matricula):null;
    }
    
    public boolean aceitarRejeitarAluguer(Cliente c){
    
        return(c.getClassificacao() > 50);
    }
    
}