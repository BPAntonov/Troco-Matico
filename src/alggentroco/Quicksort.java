/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alggentroco;

/**
 *
 * @author antonio
 */
public class Quicksort {
    private int [] vetor;
    
    public void setVetor(int [] vetor){
        this.vetor = vetor;
    
    
    }
    
    public void quicksort(int esq, int dir){
        if(esq != dir){
            int pivot = this.vetor[esq];
            //System.out.println(pivot);//Teste
            int cont_esq = esq+1;//-->
            int cont_dir = dir;//<--
            while(cont_esq < cont_dir){
                while(this.vetor[cont_dir]>pivot && cont_esq < cont_dir){//Observar controle p/ não cruzar!
                    cont_dir--;
                }
                while(this.vetor[cont_esq]<pivot && cont_esq < cont_dir){
                    cont_esq++;
                
                }
                if(this.vetor[cont_esq] >= pivot && this.vetor[cont_dir] <= pivot ){
                    
                    int aux = this.vetor[cont_esq];
                    this.vetor[cont_esq] = this.vetor[cont_dir];
                    this.vetor[cont_dir] = aux;
                    //cont_dir--;
                    //cont_esq++;
                    
                
                }
            
                
            }
            
            
            if(this.vetor[cont_dir] < pivot){
                int aux = this.vetor[esq];
                this.vetor[esq] = this.vetor[cont_dir];
                this.vetor[cont_dir] = aux;
                
            
            }else{
                int aux = this.vetor[esq];
                this.vetor[esq] = this.vetor[cont_dir-1];
                this.vetor[cont_dir-1] = aux;
                cont_esq--;//Englobar o valor não pego em uma das partições
                
            
            
            }
            //System.out.println(cont_dir);
            //System.out.println(cont_esq);
            //this.imprimirVet();//Teste
            if(esq < cont_dir-1 ){
                quicksort(esq, cont_dir-1);
            
            }
            if(cont_esq+1 < dir){
                quicksort(cont_esq+1, dir);//sem +1
            }
        }
    }
    
    public void imprimirVet(){
        for(int i = 0; i < this.vetor.length; i++){
            System.out.print(this.vetor[i] + " ");
        
        
        
        
        }
        System.out.println();
    }
    
}


/* Testar este depois

public class Quicksort  {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // check for empty or null array
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}*/