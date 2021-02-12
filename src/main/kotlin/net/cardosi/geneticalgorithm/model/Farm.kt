package net.cardosi.geneticalgorithm.model

import net.cardosi.geneticalgorithm.util.getPmmlFileModels
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

//Farm class
class Farm(popSize: Int, val requiredSpecie: String, val inputData: Map<String, Any>) {


    /**
     * @purpose Initialize population
     */
    private val harvests: MutableList<Harvest> = ArrayList()
    var fittestScore = 0

    init {
        val pmmlFileModels = getPmmlFileModels()
        IntStream.range(0, popSize)
            .forEach { harvests.add(Harvest(requiredSpecie, pmmlFileModels, inputData)) }
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