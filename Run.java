/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufg1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Chilred-pc
 */
public class Run {

    private double pc;
    private double pm;
    private double averageGeneration;
    private int run;

    public Run(int run, double pc, double pm, double initrate, int gencnt, int genlen, int max_generation, boolean protection, String replication_scheme, String recombinationScheme, Double[] rankList) throws IOException {
        this.run = run;
        // kann noch gerundet werdem(Zeit + 1 sek)
//        this.pc = round(pc,3);
//        this.pm = round(pm,3);
        this.pc = pc;
        this.pm = pm;
        int totalGeneration = 0;
        for (int running = 0; running < run; running++) {
            Genom genome = new Genom(gencnt, genlen, initrate);
            genome.setProtection(protection);
            if (protection) {
                genome.updateFitness();
                Collections.sort(genome.geneList);
            }
            int generationCounter;
            for (generationCounter = 0; generationCounter < max_generation; generationCounter++) {
                if(protection && recombinationScheme.equals("10x10")){
                    genome.updateFitness();
                    Collections.sort(genome.geneList);
                }
                if (recombinationScheme.equals("crossover")) {
                    genome.cross_two_genes(pc);
                } else if (recombinationScheme.equals("transposition")) {
                    genome.transposition(pc);
                }
                if (genome.maxFitnessReached(genlen)) {
                    break;
                }
                genome.mutate(pm);
                if (genome.maxFitnessReached(genlen)) {
                    break;
                }
                if (replication_scheme.equals("10x10")) {
                  //  genome.replication10Best();
                    genome.replication10Best_mod();

                } else if (replication_scheme.equals("RankBest")) {
//                    genome.replicateRankBased(rankList);
                    genome.replicateRankBased_mod(rankList);
                }
                //printGenome(genome);
                totalGeneration++;
                genome.setMutate();
                genome.setCross();
            }
        }
        //Ausgabe von Durschnitt
        averageGeneration = totalGeneration/this.run;
    }

    public String getResult() {

        System.out.println(pc + " " + pm + " " + averageGeneration);
        return (pc + " " + pm + " " + averageGeneration + "\r\n");
    }

    public void printGenome(Genom genome) {
        System.out.println("die staerkste Fitness:" + genome.getBestFitness());
        System.out.println("die schlechteste Fitness:" + genome.getLowestFitness());
        System.out.println("Durchschnitte Fittness: " + genome.getAverage());
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(1);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

}
