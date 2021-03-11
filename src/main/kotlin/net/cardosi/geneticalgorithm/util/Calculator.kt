package net.cardosi.geneticalgorithm.util

import net.cardosi.geneticalgorithm.model.Farm
import net.cardosi.geneticalgorithm.model.Harvest
import java.util.*


//Crossover
fun fittestOffSpring(requiredSpecie:String, numberOfGenes: Int, inputData: Map<String, Any>) : Harvest {
    val rn = Random()

    // Modified pmml files is where gene mutation actually takes place
    val pmmlFileModels = getPmmlFileModels(requiredSpecie)
    val firstOffSpring = Harvest(requiredSpecie, pmmlFileModels, inputData)
    val secondOffSpring = Harvest(requiredSpecie,  pmmlFileModels, inputData)

    //Select a random crossover point
    val crossOverPoint = rn.nextInt(numberOfGenes)
    //Swap values
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
    val fittestOffSpring = fittestOffSpring(farm.requiredSpecie, numberOfGenes, farm.inputData)
    farm.replaceFittest(fittestOffSpring)
}