package net.cardosi.geneticalgorithm.util

import net.cardosi.geneticalgorithm.model.Farm
import net.cardosi.geneticalgorithm.model.Harvest
import java.util.*


//Crossover
fun fittestOffSpring(firstFittest: Harvest, secondFittest: Harvest, numberOfGenes: Int) : Harvest {
    val rn = Random()
    //Select a random crossover point
    val crossOverPoint = rn.nextInt(numberOfGenes)
    val pmmlFileModels = getPmmlFileModels()
    //Swap values among parents
    val firstOffSpring = Harvest(firstFittest.requiredSpecie, pmmlFileModels, firstFittest.inputData)
    firstOffSpring.setGenes(firstFittest.getGenes())
    val secondOffSpring = Harvest(firstFittest.requiredSpecie,  pmmlFileModels, firstFittest.inputData)
    secondOffSpring.setGenes(secondFittest.getGenes())

    for (i in 0 until crossOverPoint) {
        val temp = firstOffSpring.getGenes()[i]
        firstOffSpring.getGenes()[i] = secondOffSpring.getGenes()[i]
        secondOffSpring.getGenes()[i] = temp
    }

    firstOffSpring.calcFitness()
    secondOffSpring.calcFitness()
    return if (firstOffSpring.fitness > secondOffSpring.fitness)  firstOffSpring else secondOffSpring
}

fun addFittestOffspring(farm : Farm, numberOfGenes: Int) {
    val firstFittest: Harvest = farm.getFirstFittest()
    val secondFittest: Harvest = farm.getSecondFittest()

    val fittestOffSpring = fittestOffSpring(firstFittest, secondFittest, numberOfGenes)

    farm.replaceFittest(fittestOffSpring)
}