package net.cardosi.geneticalgorithm

import net.cardosi.geneticalgorithm.model.Farm
import net.cardosi.geneticalgorithm.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random.Default.nextInt

class SimpleDemoGA {

    companion object {
        private var numberOfGenes = 0
        private var numberOfHarvests = 0
        private var verbose = false
        private var coloredGenes = false
        private val SPECIES = arrayOf("fern", "rose", "cactus")
//        private val requiredSpecie: String = SPECIES[nextInt(SPECIES.size)]
        private val requiredSpecie: String = "rose"
//        private val inputData: Map<String, Any> = mapOf(
//            "water" to nextInt(50) + 5,
//            "light" to nextInt(12) + 4
//        )
        private val inputData: Map<String, Any> = mapOf(
            "water" to 48,
            "light" to 5
        )

        @JvmStatic
        fun main(args: Array<String>) {
            //Set parameters here

            //Number of genes each individual has
            numberOfGenes = 3
            //Number of individuals
            numberOfHarvests = 5
            //Verbosity (e.g. Should we print genetic pool in the console?)
            verbose = true
            //Apply color to genes (if verbose = true) Note: this will slow down the process
            coloredGenes = true


            //===================

            //Initialize population
            val population = Farm(numberOfHarvests, requiredSpecie, inputData)
            printPopulation(population)

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