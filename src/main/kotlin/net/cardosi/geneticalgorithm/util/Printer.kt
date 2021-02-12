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


fun printPopulation(farm: Farm) {
    println("Population of ${farm.getIndividuals().size} individual(s).")
}

//show genetic state of the population pool
 fun printGeneticPool(harvests: List<Harvest>, coloredGenes: Boolean) {
    println("==Genetic Pool==")
    harvests.indices.forEach {
        val harvest = harvests[it]
        println("> Harvest $it | ${(if (coloredGenes) harvest.toStringColor() else harvest.toString())} |")
    }
    println("================")
}

 fun printFittestScore(farm: Farm, generationCount: Int) {
    println(
        """
                Required specie: ${farm.requiredSpecie} Environment values ${farm.inputData}
                Generation: $generationCount Fittest score: ${farm.fittestScore}""".trimIndent()
    )
}

 fun printSolution(farm: Farm, generationCount: Int, numberOfGenes: Int) {
    println(
        """
    
    Solution found in generation $generationCount
    """.trimIndent()
    )
    println("Fitness: " + farm.fittestScore)
    print("Genes: ")
    for (i in 0 until numberOfGenes) {
        print(farm.getFirstFittest().getGenes()[i])
    }
    println("")
}