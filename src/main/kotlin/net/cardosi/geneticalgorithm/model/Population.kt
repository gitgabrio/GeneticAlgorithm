package net.cardosi.geneticalgorithm.model

import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

//Population class
class Population(private val popSize: Int) {


    /**
     * @purpose Initialize population
     */
    private val individuals: MutableList<Individual> = IntStream.range(0, popSize)
        .mapToObj { Individual() }
        .collect(Collectors.toList())
    var fittestScore = 0

    init {
        updateFittest()
    }

    //Get the fittest individual and update fittest score
    fun getFirstFittest(): Individual {
        return individuals.first()
    }

    //Get the second most fittest individual
    fun getSecondFittest(): Individual {
        return individuals.elementAt(1)
    }

    fun replaceFittest(fittestOffSpring: Individual) {
        val lastIndividual =  individuals[individuals.size - 1]
        if (fittestOffSpring.fitness > lastIndividual.fitness) {
            individuals.removeAt(individuals.size - 1)
            individuals.add(fittestOffSpring)
            updateFittest()
        }
    }

    fun getIndividuals(): List<Individual> {
        return Collections.unmodifiableList(individuals)
    }

    private fun updateFittest() {
        individuals.sort()
        fittestScore = individuals[0].fitness
    }

}