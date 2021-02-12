package net.cardosi.geneticalgorithm.model

import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

//Farm class
class Farm(popSize: Int, requiredSpecie: String, inputData: Map<String, Any>) {


    /**
     * @purpose Initialize population
     */
    private val harvests: MutableList<Harvest> = IntStream.range(0, popSize)
        .mapToObj { Harvest(requiredSpecie, inputData) }
        .collect(Collectors.toList())
    var fittestScore = 0

    init {
        updateFittest()
    }

    //Get the fittest individual and update fittest score
    fun getFirstFittest(): Harvest {
        return harvests.first()
    }

    //Get the second most fittest Harvest
    fun getSecondFittest(): Harvest {
        return harvests.elementAt(1)
    }

    fun replaceFittest(fittestOffSpring: Harvest) {
        val lastHarvest =  harvests[harvests.size - 1]
        if (fittestOffSpring.fitness > lastHarvest.fitness) {
            harvests.removeAt(harvests.size - 1)
            harvests.add(fittestOffSpring)
            updateFittest()
        }
    }

    fun getIndividuals(): List<Harvest> {
        return Collections.unmodifiableList(harvests)
    }

    private fun updateFittest() {
        harvests.sort()
        fittestScore = harvests[0].fitness
    }

}