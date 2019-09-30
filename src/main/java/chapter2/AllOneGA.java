package chapter2;

/**
 * @Author: David
 * @Date: 2019/9/29  15:07
 * @Version 1.0
 */
public class AllOneGA {
    //记录当前代
    static int generation = 1;

    public static void main(String[] args) {
        //创建一个Ga对象
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.85, 0);

        //初始化一个指定染色体长度的种群(已经初始化所有随机个体的基因（1000011..50）)
        Population population = ga.initPopulation(50);

        //计算种群适应度 计算适应度的同时，计算每个个体的适应度 并存入个体
        ga.evalPopulation(population);

        //终止
        while (ga.isTerminationConditionMet(population) == false) {
            //打印这个种群里面最大的种群适应度 【重要将种群按照适应度排列了】
            System.out.println("Best solution: " + population.getFittest(0).toString());

            //交叉（可以将他的解 停滞在局部最优）
            population = ga.crossoverPopulation(population);
            //变异 （可以将他的解踢向更好的解）

            //计算种群的适应度
            ga.evalPopulation(population);
            generation++;
        }

        System.out.println("找到第几代的结果"+generation+"");
        System.out.println("种群最好的适应度"+population.getFittest(0).toString());
    }
}
