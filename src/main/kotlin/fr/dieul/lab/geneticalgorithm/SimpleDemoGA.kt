package fr.dieul.lab.geneticalgorithm

import fr.dieul.lab.geneticalgorithm.model.Population
import fr.dieul.lab.geneticalgorithm.util.addFittestOffspring
import fr.dieul.lab.geneticalgorithm.util.printFittestScore
import fr.dieul.lab.geneticalgorithm.util.printGeneticPool
import fr.dieul.lab.geneticalgorithm.util.printSolution
import java.util.concurrent.atomic.AtomicInteger

class SimpleDemoGA {


    companion object {
        private var numberOfGenes = 0
        private var numberOfIndividuals = 0
        private var verbose = false
        private var coloredGenes = false

        @JvmStatic
        fun main(args: Array<String>) {
            //Set parameters here

            //Number of genes each individual has
            numberOfGenes = 20
            //Number of individuals
            numberOfIndividuals = 5
            //Verbosity (e.g. Should we print genetic pool in the console?)
            verbose = true
            //Apply color to genes (if verbose = true) Note: this will slow down the process
            coloredGenes = true

            //===================

            //Initialize population
            val population = Population(numberOfIndividuals, numberOfGenes)
            println("Population of " + population.getIndividuals().size + " individual(s).")

            //Select fittest
            population.getFirstFittest()
            printFittestScore(population, 0)

            if (verbose) {
                //show genetic pool
                printGeneticPool(population.getIndividuals(), coloredGenes)
            }

            val counter = AtomicInteger()
            //While population gets an individual with maximum fitness
            while (population.fittestScore < numberOfGenes) {
                counter.addAndGet(1)

                //Add fittest offspring to population
                addFittestOffspring(population, numberOfGenes)

                printFittestScore(population, counter.get())

                if (verbose) {
                    //show genetic pool
                    printGeneticPool(population.getIndividuals(), coloredGenes)
                }
            }
            printSolution(population, counter.get(), numberOfGenes)
        }

    }

}