package chapter2;

/**
 * @Author: David
 * @Date: 2019/9/29  14:06
 * @Version 1.0
 */
public class GeneticAlgorithm {
    /**
     * 种群规模
     */
    private int popilationSize;
    /**
     * 变异概率（率）
     */
    private double mutationRate;
    /**
     * 交叉概率（率）
     */
    private double crossoverRate;
    /**
     * 精英计数（一开始设置为0意思是暂且禁用它）
     */
    private int elitismCount;

    public GeneticAlgorithm(int popilationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.popilationSize = popilationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

    /**
     * 初始化指定染色体的长度的种群
     */
    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.popilationSize, chromosomeLength);
        return population;
    }


    /*
    * =====-评估-=====
    * */
    /**
     * 适应度函数，计算个体的适应度 并存储适应度
     * “全一” ：计算个体染色体1的个数的
     */
    public double calcFitness(Individual individual) {
        int correctGenes = 0;
        for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
            if (individual.getGene(geneIndex) == 1) {
                correctGenes += 1;
            }
        }
        //计算适应度
        double fitness = (double)correctGenes/individual.getChromosomeLength();
        //存储适应度
        individual.setFitness(fitness);
        return fitness;
    }

    /**
     * 计算种群的适应度
     * @param population
     * @return
     */
    public void evalPopulation(Population population) {
        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        //存储适应度
        population.setPopulationFitness(populationFitness);
    }

    /*===-终止检查-===*/
    //本题中是适应度为1时（也就是个体的染色体全1时）
    public boolean isTerminationConditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 1) {
                return true;
            }
        }
        return false;
    }

    /*===-轮盘赌注-===*/

    //选取双亲
    public Individual selectParent(Population population) {
        Individual[] individuals = population.getIndividuals();
        double populationFitness = population.getPopulationFitness();
        //将种族适应度 取一个随机数据
        double routeteWheelPositon = Math.random() * populationFitness;

        //当个体的
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= routeteWheelPositon) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }

    //交叉
    public Population crossoverPopulation(Population population) {
        //新建立一个和原来一样规模的总群
        Population newPopulation = new Population(population.size());

        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);
            //如果交叉概率发生，且不是精英
            if (this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
                //子代（子代的染色体长度肯定一样）
                Individual offspring = new Individual(parent1.getChromosomeLength());
                //
                Individual parent2 = selectParent(population);
                //子代基因是双亲的基因获取
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {

                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                //将交叉基因后的个体加入到新的种群汇总
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                //将个体直接加入新的种群（精英主义/没有发生交叉概率）
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    /**
     * 变异
     * @param population
     * @return
     */
    public Population mutatePopulation(Population population) {
        //// TODO: 2019/10/9
        return null;
    }



}
