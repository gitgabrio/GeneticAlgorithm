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

//show genetic state of the population pool
 fun printGeneticPool(individuals: List<Individual>, coloredGenes: Boolean) {
    println("==Genetic Pool==")
    individuals.indices.forEach {
        val individual = individuals[it]
        println("> Individual $it | ${(if (coloredGenes) individual.toStringColor() else individual.toString())} |")
    }
    println("================")
}

 fun printFittestScore(population: Population, generationCount: Int) {
    println(
        """
                
                Generation: $generationCount Fittest score: ${population.fittestScore}""".trimIndent()
    )
}

 fun printSolution(population: Population, generationCount: Int, numberOfGenes: Int) {
    println(
        """
    
    Solution found in generation $generationCount
    """.trimIndent()
    )
    println("Fitness: " + population.fittestScore)
    print("Genes: ")
    for (i in 0 until numberOfGenes) {
        print(population.getFirstFittest().getGenes()[i])
    }
    println("")
}