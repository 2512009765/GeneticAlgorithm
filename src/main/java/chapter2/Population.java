package chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 就是保存由个体构成的一个数组，并便捷的访问
 * @Author: David 类似一个list
 * @Date: 2019/9/29  15:26
 * @Version 1.0
 */
public class Population {
    /**
     * 个体集合
     */
    private Individual population[];
    /**
     * 总群的适应度？初始化的时候有设置种群适应度吗
     */
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize,int chromosomeLength) {
        this.population = new Individual[populationSize];
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            Individual individual = new Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }

    public Individual[] getIndividuals() {
        return population;
    }

    /**
     * 获取该种群最大适应度的个体?获取适应度排名第几的个体
     * @param offset
     * @return
     */
    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if(o1.getFitness() < o2.getFitness()){
                    return 1;//置换顺序
                }
                return 0;//置换顺序
            }
        });
        return this.population[offset];
    }

    public Individual[] getPopulation() {
        return population;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }

    public double getPopulationFitness() {
        return populationFitness;
    }

    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    /**
     * 获取该种群的个体数量
     * @return
     */
    public int size() {
        return this.population.length;
    }

    public Individual setIndividual(int offet,Individual individual) {
        return population[offet] = individual;
    }
    public Individual getIndividual(int offet) {
        return population[offet];
    }

    /**
     * 洗牌，把种群里面的个体洗牌
     */
    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length-1 ; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }

}
