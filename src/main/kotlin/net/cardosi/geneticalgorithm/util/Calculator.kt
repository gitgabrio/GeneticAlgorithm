/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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