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

import net.cardosi.geneticalgorithm.model.Harvest
import net.cardosi.geneticalgorithm.model.Farm
import java.util.*


//Crossover
fun fittestOffSpring(firstFittest: Harvest, secondFittest: Harvest, numberOfGenes: Int) : Harvest {
    val rn = Random()
    //Select a random crossover point
    val crossOverPoint = rn.nextInt(numberOfGenes)
    //Swap values among parents
    val firstOffSpring = Harvest(firstFittest.requiredSpecie, firstFittest.inputData)
    firstOffSpring.setGenes(firstFittest.getGenes())
    val secondOffSpring = Harvest(firstFittest.requiredSpecie, firstFittest.inputData)
    secondOffSpring.setGenes(secondFittest.getGenes())

    for (i in 0 until crossOverPoint) {
        val temp = firstOffSpring.getGenes()[i]
        firstOffSpring.getGenes()[i] = secondOffSpring.getGenes()[i]
        secondOffSpring.getGenes()[i] = temp
    }

    //Do mutation under a random probability
    if (rn.nextInt() % 7 < 5) {
        mutation(firstOffSpring, secondOffSpring, numberOfGenes)
    }

    firstOffSpring.calcFitness()
    secondOffSpring.calcFitness()
    return if (firstOffSpring.fitness > secondOffSpring.fitness)  firstOffSpring else secondOffSpring
}

//Mutation
fun mutation(firstFittest: Harvest, secondFittest: Harvest, numberOfGenes: Int) {
    val rn = Random()

    //Select a random mutation point
    var mutationPoint = rn.nextInt(numberOfGenes)
    //Flip values at the mutation point
    if (firstFittest.getGenes()[mutationPoint] == 0) {
        firstFittest.getGenes()[mutationPoint] = 1
    } else {
        firstFittest.getGenes()[mutationPoint] = 0
    }
    mutationPoint = rn.nextInt(numberOfGenes)
    if (secondFittest.getGenes()[mutationPoint] == 0) {
        secondFittest.getGenes()[mutationPoint] = 1
    } else {
        secondFittest.getGenes()[mutationPoint] = 0
    }
}

fun addFittestOffspring(farm : Farm, numberOfGenes: Int) {
    val firstFittest: Harvest = farm.getFirstFittest()
    val secondFittest: Harvest = farm.getSecondFittest()

    val fittestOffSpring = fittestOffSpring(firstFittest, secondFittest, numberOfGenes)

    farm.replaceFittest(fittestOffSpring)
}