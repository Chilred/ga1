package gen_aufg1;

import java.util.Arrays;

public class Gene implements Comparable<Gene> {

    int[] value;
    boolean protectGene = false;
    int fitness;

    public Gene(int genlen) {
        value = new int[genlen];
    }
    
    public void setProtectGene(Boolean status) {
        this.protectGene = status;
    }

    public boolean getProtectGene() {
        return this.protectGene;
    }

    public int getFitness() {
        return this.fitness;
    }
    
    public void updateFitness(){
        int fit = 0;
        for (int i = 0; i < value.length; i++) {
            fit = fit + value[i];       
        }
        this.fitness = fit;
    }

    public String toString() {
        String genString = "";
//		to show all genes
//		for (int i = 0; i < value.length;i++){ 
//			genString = genString + value[i];
//		}
        //genString = genString + " Fitness= " + getFitness();
        genString = genString + getFitness();
        return genString;
    }

    public void set_gene_pos(int pos, int new_value) {
        if (new_value == -1) {
            if (this.value[pos] == 0) {
                this.value[pos] = 1;
            } else {
                this.value[pos] = 0;
            }
        } else if (new_value == 0 || new_value == 1) {
            this.value[pos] = new_value;
        }
    }

    public int get_gene_pos(int pos) {
        return this.value[pos];
    }

    public void cross_two_genes(Gene gen2, int position) {
        // alte Methode vor dem Profiling
        int temp;
        for (int i = position; i < this.value.length; i++) {
            temp = this.get_gene_pos(i);
            set_gene_pos(i, gen2.get_gene_pos(i));
            gen2.set_gene_pos(i, temp);
        }
    }

    public void cross_two_genes_mod(Gene gen2, int position) {
        // neue Methode nach dem Profiling
        int[] tempValue = new int[this.value.length];
        System.arraycopy(gen2.value, position, tempValue, position, value.length-position);
        System.arraycopy(this.value, position, gen2.value, position, value.length-position);
        System.arraycopy(tempValue, position, this.value, position, value.length-position);
    }

    public void transposition(Gene gen2, int posStart, int posEnd) {
        // alte Methode vor dem Profiling
        for (int i = posStart; i < posEnd; i++) {
            gen2.set_gene_pos(i, this.get_gene_pos(i));
        }
    }
    
    public void transposition_mod(Gene gen2, int posStart, int splitLength) {
        // neue Methode nach dem Profiling
        System.arraycopy(this.value, posStart, gen2.value, posStart, splitLength);
    }

    @Override
    public int compareTo(Gene arg0) {   // zum sortieren nach Gene
        // TODOAuto-generated method stub
        if (this.getFitness() > arg0.getFitness()) {
            return 1;
        } else if (this.getFitness() < arg0.getFitness()) {
            return -1;
        }
        return 0;
    }
}
