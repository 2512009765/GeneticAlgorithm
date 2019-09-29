package chapter2;

import java.util.Arrays;

/**
 * 个体:代表一个候选解，主要是负责存储和操作一条染色体 和追踪个体的适应度
 * 个体,没有什么真正的意义，就是存储的容器
 * @Author: David
 * @Date: 2019/9/29  15:12
 * @Version 1.0
 */

public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public Individual(int chromosomeLength) {
        this.chromosome= new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < Math.random()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    private void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public int getChromosomeLength() {
        return chromosome.length;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.chromosome.length; i++) {
            output += this.chromosome[i];
        }
        return output;
    }

}
