/*
 */
package alggentroco;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
//import java.math.*;

/**
 *
 * @author antonio
 */
public class Troco {

    
    //Gets e Sets
    /**
     * @return the melhoresSelecoes
     */
    public int[] getMelhoresSelecoes() {
        return melhoresSelecoes;
    }

    /**
     * @param melhoresSelecoes the melhoresSelecoes to set
     */
    public void setMelhoresSelecoes(int[] melhoresSelecoes) {
        this.melhoresSelecoes = melhoresSelecoes;
    }

    /**
     * @return the mediaMelhoresGeracoes
     */
    public int[] getMediaMelhoresGeracoes() {
        return mediaMelhoresGeracoes;
    }

    /**
     * @param mediaMelhoresGeracoes the mediaMelhoresGeracoes to set
     */
    public void setMediaMelhoresGeracoes(int[] mediaMelhoresGeracoes) {
        this.mediaMelhoresGeracoes = mediaMelhoresGeracoes;
    }

    /**
     * @return the inicialAleatorio
     */
    public int getInicialAleatorio() {
        return inicialAleatorio;
    }

    /**
     * @param inicialAleatorio the inicialAleatorio to set
     */
    public void setInicialAleatorio(int inicialAleatorio) {
        this.inicialAleatorio = inicialAleatorio;
    }

    /**
     * @return the porcentagemmutacao
     */
    public double getPorcentagemmutacao() {
        return porcentagemmutacao;
    }

    /**
     * @return the valortrocoT
     */
    public int getValortrocoT() {
        return valortrocoT;
    }

    /**
     * @param valortrocoT the valortrocoT to set
     */
    public void setValortrocoT(int valortrocoT) {
        this.valortrocoT = valortrocoT;
    }

    /**
     * @param porcentagemmutacao the porcentagemmutacao to set
     */
    public void setPorcentagemmutacao(double porcentagemmutacao) {
        this.porcentagemmutacao = porcentagemmutacao;
    }

    /**
     * @param porcentagemcrossover the porcentagemcrossover to set
     */
    public void setPorcentagemcrossover(double porcentagemcrossover) {
        this.porcentagemcrossover = porcentagemcrossover;
    }

    /**
     * @param quantCromossomos the quantCromossomos to set
     */
    public void setQuantCromossomos(int quantCromossomos) {
        this.quantCromossomos = quantCromossomos;
    }

    /**
     * @param geracoes the geracoes to set
     */
    public void setGeracoes(int geracoes) {
        this.geracoes = geracoes;
    }
    
    private int valortrocoT;//O valor de troco à contabilizar
    private int [] saidasV;//Saída final = Quantidade de notas de cada tipo e valor final
    private int [] melhoresSelecoes;//Guarda os melhores valores obtidos nos processos de seleção. É usado para a geração do gráfico.
    private int [] mediaMelhoresGeracoes;//Guarda a média de todos os valores por cada seleção. É usado para a geração do gráfico.
    private int [] cedulas;//Valores das cedulas Ex.: {1, 2, 5, 10, 20, 50, 100};
    private double porcentagemmutacao;//Define a porcentagem usada na mutacao
    private double porcentagemcrossover;//Define a porcentagem usada no crossover
    private int quantCromossomos;//Quantidade de cromossomos serão definidos para o algoritmo
    private int inicialAleatorio;//Controla os valores aleatorios que serão gerados
    private int geracoes;//Quantidade de gerações
    
    public Troco(){//Construtor
        this.valortrocoT = 0;
        this.saidasV = new int [8];// O último valor é o valor total em dinheiro
        this.porcentagemmutacao = 50;
        this.porcentagemcrossover = 50;
        
        this.cedulas = new int [7];
        int [] vcedulas = {1, 2, 5, 10, 20, 50, 100};
        this.melhoresSelecoes = new int[20];
        this.mediaMelhoresGeracoes = new int [20];
        this.cedulas = vcedulas;
        
        this.quantCromossomos = 20;
        this.inicialAleatorio = 1;
        this.geracoes = 1;
        
        
    
    
    }
    
    public int[][] gerarPopInicial(){//Método para geração da população inicial
        //1; 2; 5; 10; 20; 50; 100;
        int[][] popinicial = new int[this.quantCromossomos][7];//População inicial = Cromossomos X Quantidade de Cédulas
        Random rand = new Random();
        for(int i = 0; i < this.quantCromossomos; i++){
            for(int j = 0; j < 7; j++){
                //System.out.println(j);
                int tempint = Math.abs(this.getInicialAleatorio()/(this.cedulas[j]))+1;//Melhorou um pouco a precisão
                //System.out.println(tempint);
                int aleatoriointeiro = rand.nextInt(tempint);//int aleatoriointeiro = rand.nextInt(this.getInicialAleatorio() + this.getInicialAleatorio()/10);
                popinicial[i][j] = (aleatoriointeiro);//Para aleatorizar melhor //(aleatoriointeiro / Math.abs((this.cedulas[j])))
                //System.out.println(aleatoriointeiro);
            }
        }
        //imprimeMat(popinicial);
        return popinicial;
    
    
    
    
    }
    
    public boolean impar(int val){// Teste de par ou impar
        if(val%2 == 0){
        
            return false;
        }
        return true;
    
    }
    
    public int[][] gerarCrossover(int[][] popinicial){//Gera um novo conj. de cromossomos, a partir da op. de crossover. Implementa o crossover de 1 ponto
        double valorcrossover = this.porcentagemcrossover/100;//Cálculo de porcentagem
        
        int qtdgenes = (int)(Math.floor(valorcrossover*7));//Convertemos para inteiro
        System.out.println("Genes para crossover: " +qtdgenes);
        
        int [][] crossover = new int [this.quantCromossomos][7];//Mesmas especificações da matriz de população inicial
        
        for(int i = 0; i < this.quantCromossomos; i++){
            for(int j = qtdgenes; j < 7; j++){
            
                crossover[i][j] = popinicial[i][j];         
            }
        }
     
        for(int i = 0; i < this.quantCromossomos; i++){
            for(int j = 0; j < qtdgenes; j++){
                if(impar(i)){//Efetuamos o Crossover assim
                  crossover[i][j] = popinicial[i-1][j];
                
                
                
                }else{
                  crossover[i][j] = popinicial[i+1][j];         
                }  
            }
        
        
        }
        
        
        
        return crossover;
    
    
    }
    
    public int[][] gerarMutacao(int[][] popinicial){//Gera mutacao conforme a porcentagem informada
        double valormutacao = this.getPorcentagemmutacao()/100;
        int qtdgenes = (int)Math.floor(valormutacao*7);//Quantidade de genes à serem mutados
        System.out.println("Genes para mutacao: " +qtdgenes);
        Random rand = new Random();
        int [][] mutacao = new int[this.quantCromossomos][7];   
        for(int i = 0; i < this.quantCromossomos; i++){//Cópia da população Inicial
            for(int j = 0; j < 7; j++){   
                mutacao[i][j] = popinicial[i][j];
            }
        }
        //this.imprimeMat(mutacao);
        int i = 0;
        while(i<this.quantCromossomos){
            int contgenes = qtdgenes;
            int [] controleGenes = {0, 0, 0, 0, 0, 0, 0};   
            while(contgenes > 0){
                int alteragene = rand.nextInt(this.getInicialAleatorio());//Valores entre 1 e 10
                int geneaalterar = rand.nextInt(6);
                if(controleGenes[geneaalterar] == 1){//Significa que tal gene já foi alterado...
                    int genealternativo = 0;//Trabalhamos com outra alternativa
                    while(controleGenes[genealternativo] == 1 && genealternativo < 7){//Encontra a alternativa 
                        genealternativo++;
                        if(genealternativo == 7){
                            genealternativo--;
                            while(controleGenes[genealternativo] == 1 && genealternativo >= 0){//Indo para ambos os "lados"
                                genealternativo--;
                            }
                        }
                    }
                    geneaalterar = genealternativo; // Acha um qualquer ue ainda esteja sem alterar
                }
                
                controleGenes[geneaalterar] = 1;//Marcamos como já alterado
                // gene - aleatorio/geneaalterar - Alteramos isto aqui pra aleatorizar melhor
                mutacao[i][geneaalterar] = alteragene / (this.cedulas[geneaalterar]);///mutacao[i][geneaalterar] + alteragene     
                contgenes--;    
            }
            i++;
        }
        return mutacao;
    
    }
    
    public int [][] joinMatrizes(int [][] popinicial, int[][] crossover, int [][] mutacao){//Retorna uma matriz 12x7 com todo mundo junto
        int [][] matAvaliacao = new int [(this.quantCromossomos*3)][7];
        for(int i = 0; i<this.quantCromossomos; i++){//Se liga na mágica
            for(int j = 0; j<7; j++){//Junta as 3 para trabalho
                matAvaliacao[i][j] = popinicial[i][j];
                matAvaliacao[i+this.quantCromossomos][j] = crossover[i][j];
                matAvaliacao[i+(this.quantCromossomos*2)][j] = mutacao [i][j];
            
            
            
            }
        }
        
        return matAvaliacao;
    }//Para facilitar nosso trato do fitness
    
    public int [][] Fitness(int [][] matAvaliacao){//Alterar tudo aqui depois, só pra ter mesmo
        int [][] vfitness = new int [(this.quantCromossomos*3)][2];//12 cromossomos x coluna com valor e coluna com posicao

        
        for(int i = 0; i < (this.quantCromossomos*3); i++){
            int somalinha = 0;
            int colmultvalor = 0;
            for(int j = 0; j < 7; j++){
                somalinha += matAvaliacao[i][j];//sum(qi)
                colmultvalor += matAvaliacao[i][j] * this.cedulas[j];//sum(qi vi) 
            
            
            }
            vfitness[i][0] = somalinha + (Math.abs(this.getValortrocoT() - colmultvalor) * this.getValortrocoT());//Funcao de fitness
            vfitness[i][1] = i;//Marcador
        
        
        }
        return vfitness;
        
    }
    
    public int[][] selecao (int [][] matAvaliacao){//Fazer Retorna os cromossomos selecionados p próxima geracao
        int [][] vfitness = Fitness(matAvaliacao);
        
        int [] fitnessparacomparacao = new int [(this.quantCromossomos*3)];
        for(int i = 0; i < fitnessparacomparacao.length; i++){//Copiando os valores de fitness
            fitnessparacomparacao[i] = vfitness[i][0];
        }
        
        //System.out.println("\n\n"+fitnessparacomparacao[5]);//Teste
        fitnessparacomparacao = this.quickSortVet(fitnessparacomparacao, 0, fitnessparacomparacao.length - 1);//Ordenação, para escolhermos os melhores depois
        //imprimirVetSelecao(fitnessparacomparacao);
        //Fazer quicksort + rápido
        //System.out.println("Pato5");
        
        int [][] valorselecaofim = new int [this.quantCromossomos][2];//Valores de fitness de quem foi escolhido e os marcadores
        for(int i = 0; i < this.quantCromossomos; i++){//Agora, pegamos os 4 melhores
            valorselecaofim[i][0] = fitnessparacomparacao[i];
            for(int j = 0; j < (this.quantCromossomos*3); j++){//Vamos querer descobrir os marcadores tambem
                if(fitnessparacomparacao[i] == vfitness[j][0]){
                    valorselecaofim[i][1] = vfitness[j][1];//Pega o marcador
                    
                
                }
            
            
            
            }
        
        }
        System.out.println("\nEscolhidos: ");
        imprimirVetSelecaoFim(valorselecaofim);
        
        int [][] selecao = constroiSelecao(matAvaliacao, valorselecaofim);//Saída final, será considerada a população inicial da próxima geração
        System.out.println("\nSeleção: ");
        imprimeMat(selecao);//imprimirVetSelecaoSaida(selecao);
        
        System.out.println("\nSeleção com Fitness: ");
        imprimirSelecaoEFitness(valorselecaofim, selecao);
        
        return selecao;
    
    }
    
    public void imprimirSelecaoEFitness(int [][] valorselecaofim, int [][] selecao){
        for(int i = 0; i < this.quantCromossomos; i++){
            System.out.print("Fitness: " + valorselecaofim[i][0] + " | Cromossomos: ");
            for(int j = 0; j < 7; j++){
                System.out.print("["+selecao[i][j]+"]");
            
            
            
            
            }
            System.out.println();
        
        }
    
    
    
    }
    
    public int [][] constroiSelecao(int [][] matAvaliacao, int [][] valorselecaofim){//Constroi a resposta do processo de seleção
        int [][] selecao = new int[(this.quantCromossomos)][7];
        for(int i = 0; i < (this.quantCromossomos); i++){
        
            for(int j = 0; j < 7; j++){
                selecao[i][j] = matAvaliacao[valorselecaofim[i][1]][j]; //Usando o marcador aqui!

            }

        }
        
        return selecao;
    }
    
    public void heurPorcentagemMutacao(int ger){//Pela geração, define qual será a porcentagem de mutação. É uma heurística
            if(ger > 5){//Mais de 5 gerações
                this.setPorcentagemmutacao(Math.abs(this.getPorcentagemmutacao()/1.5));
            
            
            }
            if(ger > 10){//Mais de 10 gerações
                this.setPorcentagemmutacao(Math.abs(this.getPorcentagemmutacao()/2.5));
            
            
            }
    
    
    
    }
    
    public void heurGeracoes(boolean usarHeurGeracoes){//Se o usuário desejar, usará esta heurisitica para quantidade de gerações
        if(usarHeurGeracoes){
            this.setGeracoes((int) (Math.log(this.getValortrocoT()) / Math.log(2)));//Aproximação pela função log
            //System.out.println("Pato");
        }
    
    }//Caso o usuário não use esta heurística, a quantidade de gerações será a especificiada por ele na interface gráfica
    
    public int geraMedia(int [][] selecao){//Calcula média dos valores selecionados em matriz (media = somadetodos/quantidade)
        int aux = 0;//Armazenará nossa soma
        for(int i = 0; i < this.quantCromossomos; i++){
            for(int j = 0; j< this.cedulas.length; j++){//Aqui
                aux += selecao[i][j]*this.cedulas[j];//Calcula o valor (qtcedula*valorcedula) do cromossomo
            
            
            }
        
        
        }
        return(aux/this.quantCromossomos);
    
    }
    
    public void preparacaoGrafico(int ger, int [][] teste){//Prepara para a criação do gráfico após execução
        if(ger == 0){//Somente para a primeira geração
            this.setMediaMelhoresGeracoes(new int [this.geracoes]);//Inicia com a quantidade de gerações. Para cada geração, irá armazenar a entre os resultados selecionados.
            this.setMelhoresSelecoes(new int [this.geracoes]);//Inicia com a quantidade de gerações. Armazena o melhor valor de cada geração.
        }
        
        for(int j = 0; j < this.cedulas.length; j++){
            this.getMelhoresSelecoes()[ger] += teste[0][j] * this.cedulas[j];
            //System.out.println(this.getMelhoresSelecoes()[ger] + "\n\n\n");
        }
        this.getMediaMelhoresGeracoes()[ger] = geraMedia(teste);
       // System.out.println(this.mediaMelhoresGeracoes[ger]+ "\n\n\n");
    }
    
    
    public void driver(){//Construir todo o processo aqui!//Driver é aonde todo o processo ocorre.
        //this.setValortrocoT(55);//Teste
        //this.inicialAleatorio = 55;
        //this.setGeracoes((int) (Math.log(this.getValortrocoT()) / Math.log(2)));
  
        
        int [][] pinicialteste = this.gerarPopInicial();
        int [][] teste = pinicialteste;
        for(int ger = 0; ger<this.geracoes; ger++){//Melhorar esta condição//Com 15 deu
            
            heurPorcentagemMutacao(ger);
            System.out.println("##################");
            System.out.println("Geração: "+(ger+1));
            int [][] crossteste = this.gerarCrossover(teste);
            int [][] mutacaoteste = this.gerarMutacao(teste);
            //System.out.println("Pato1");
            System.out.println("----------");
            System.out.println("\nPopulacao: ");
            imprimeMat(teste);
            System.out.println("\nCrossover: ");
            imprimeMat(crossteste);
            System.out.println("\nMutacao: ");
            imprimeMat(mutacaoteste);
            int[][] joinmatteste = this.joinMatrizes(teste, crossteste, mutacaoteste);
            
            System.out.println("\nMatrizes Agrupadas: ");
            imprimeJoinMat(joinmatteste);
            //System.out.println("Pato2");
            int [][] vfitnessteste = Fitness(joinmatteste);
            /*imprimeMatFitness(vfitnessteste);*/
            //System.out.println("Pato3");
            teste = selecao(joinmatteste); // Deixar mais leve
            //System.out.println("Pato4");
            /*System.out.println("Final");
            imprimeMat(teste);*/
            preparacaoGrafico(ger, teste);
            System.out.println("##################");
        }
        System.out.println("\nFinal");
        imprimeMat(teste);
        valoresFinaisTroco(teste);
        imprimirNotaseValores(teste);
        this.setSaídasV(teste);//Grava a saída e a resolução encontrada
        
        this.gerarCSVMelhoresGer();
        this.gerarCSVMelhoresMed();
        
        for(int i = 0; i < 8; i++ ){
            System.out.print(this.saidasV[i]+ " ");
        
        
        }
    }
    
    public void setSaídasV(int [][] saidasV){//Constroi a saída para o atributo
        for(int i = 0; i < this.saidasV.length-1; i++){
            this.saidasV[i] = saidasV[0][i]; 
        
        
        }
        for(int i = 0; i < this.cedulas.length; i++){
            this.saidasV[this.saidasV.length-1] += saidasV[0][i]*this.cedulas[i]; 
        }
    }
    
    public void valoresFinaisTroco(int [][] selecao){
        
        for(int i = 0; i < (this.quantCromossomos); i++){
            int aux = 0;
            for(int j = 0; j < 7; j++ ){
                aux += selecao[i][j] * this.cedulas[j];
                
            }
            System.out.println(i+" "+aux);
            
           
        }
    }
    
    public void imprimirNotaseValores(int [][] matriz){//Imprime de forma mais "agradável" a quantidade por cédula juntamente com o valor
        System.out.println("\nResultado: ");
        System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos); i++){
            int aux = 0;
            for(int j = 0; j<7; j++){
                System.out.print("["+matriz[i][j]+"]");
                aux += matriz[i][j] * this.cedulas[j];
            
            }
            System.out.println(" | Valor:  " + aux);
        
        
        
        }
        System.out.println("----------");
    
    
    
    
    }
    
    public String imprimirNotaseValoresStr(int [][] matriz){//Imprime de forma mais "agradável" a quantidade por cédula juntamente com o valor
        String textoSaida = "";
        textoSaida = textoSaida.concat("\nResultado: \n ----------");
        
        System.out.println("\nResultado: ");
        System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos); i++){
            int aux = 0;
            for(int j = 0; j<7; j++){
                System.out.print("["+matriz[i][j]+"]");
                aux += matriz[i][j] * this.cedulas[j];
            
            }
            System.out.println(" | Valor:  " + aux);
        
        
        
        }
        System.out.println("----------");
    
    
    
        return  "aaa";
    }
    
    /////////////////////////////////////// Métodos de teste
    public void imprimeMat(int[][] matriz){//Imprime uma matriz Cromossomos X Tipo de cédula
        System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos); i++){
            for(int j = 0; j<7; j++){
                System.out.print("["+matriz[i][j]+"]");
            
            
            }
            System.out.println();
        
        
        
        }
        System.out.println("----------");

    }
    
    public void imprimeJoinMat(int[][] joinMat){//Imprime a matriz que é composta por P. INICIAL + CROSSOVER + MUTAÇÃO
        System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos*3); i++){
            for(int j = 0; j<7; j++){
                System.out.print("["+joinMat[i][j]+"]");
            
            
            }
            System.out.println();
        
        
        
        }
        System.out.println("----------");
    
    
    
    
    }
    
    public void imprimeMatFitness(int[][] matFitness){//Imprime a Matriz que guarda os resultados da função Fitness
       System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos*3); i++){
            for(int j = 0; j<2; j++){//Differente
                System.out.print("["+matFitness[i][j]+"]");
            
            
            }
            System.out.println();
        
        
        
        }
        System.out.println("----------");
    
    
    
    
    }
    
    public void imprimirVetSelecao(int [] vetselecao){
        for(int i = 0; i < vetselecao.length; i++){
            System.out.print("["+vetselecao[i]+"]");
    
    
    
         }
        System.out.println();
    
    
    }
    
    public void imprimirVetSelecaoFim(int [][] vetselecaofim){
     System.out.println("----------");
        for(int i = 0; i<(this.quantCromossomos); i++){
            for(int j = 0; j<2; j++){//Differente
                System.out.print("["+vetselecaofim[i][j]+"]");
            
            
            }
            System.out.println();
        
        
        
        }
        System.out.println("----------");
    
    
    
    
    }
    
    public void gerarCSVMelhoresMed(){//Gerar um .csv contendo os valores das médias dos valores selecionados por cada geração
        File file = new File("melhoresmedias.csv");
        try{
            FileWriter f1 = new FileWriter(file);
            
            for(int i = 0; i < this.mediaMelhoresGeracoes.length; i++){
                f1.write((i+1)+";"+mediaMelhoresGeracoes[i]+";\n");
            
            
            
            }
            f1.flush();
            f1.close();
        
        }catch(Exception e){
        
        }
    }
    
    public void gerarCSVMelhoresGer(){//Gerar um .csv com os melhores de cada geração
        File file = new File("melhores.csv");
        try{
            FileWriter f1 = new FileWriter(file);
            
            for(int i = 0; i < this.melhoresSelecoes.length; i++){
                f1.write((i+1)+";"+melhoresSelecoes[i]+";\n");
            
            
            
            }
            f1.flush();
            f1.close();
        
        }catch(Exception e){
        
        }
    
    
    
    
    }

    ////////////////////////////////////
    
    public int [] quickSortVet(int [] vet, int inicio, int fim){//Acesso à outra classe
        /*Quicksort q1 = new Quicksort();//Ordenamos
        q1.setVetor(vet);
        q1.quicksort(inicio, fim);//geralmente 0 e tamanho -1
        return vet;*/
        
        Quicksort2 q1 = new Quicksort2();
        q1.sort(vet);
        vet = q1.getSorted();
        
        
        
        return vet;
    
    }
    
    
}
