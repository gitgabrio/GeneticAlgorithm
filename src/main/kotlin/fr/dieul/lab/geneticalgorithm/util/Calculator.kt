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
package fr.dieul.lab.geneticalgorithm.util

import fr.dieul.lab.geneticalgorithm.model.Individual
import fr.dieul.lab.geneticalgorithm.model.Population
import java.util.*


//Crossover
fun fittestOffSpring(firstFittest: Individual, secondFittest: Individual, numberOfGenes: Int) : Individual {
    val rn = Random()
    //Select a random crossover point
    val crossOverPoint = rn.nextInt(numberOfGenes)
    //Swap values among parents
    val firstOffSpring = Individual(numberOfGenes)
    firstOffSpring.setGenes(firstFittest.getGenes())
    val secondOffSpring = Individual(numberOfGenes)
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
fun mutation(firstFittest: Individual, secondFittest: Individual, numberOfGenes: Int) {
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

fun addFittestOffspring(population : Population, numberOfGenes: Int) {
    val firstFittest: Individual = population.getFirstFittest()
    val secondFittest: Individual = population.getSecondFittest()

    val fittestOffSpring = fittestOffSpring(firstFittest, secondFittest, numberOfGenes)

    population.replaceFittest(fittestOffSpring)
}