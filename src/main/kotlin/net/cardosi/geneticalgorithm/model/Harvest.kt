package net.cardosi.geneticalgorithm.model

import net.cardosi.geneticalgorithm.features.*
import net.cardosi.geneticalgorithm.util.ConsoleColors
import kotlin.Comparable as Comparable1

class Harvest(val requiredSpecie: String, val inputData: Map<String, Any>) : Comparable1<Harvest> {

    var fitness = 0
    private val features: Map<Int, AbstractGreenhouse> = mapOf(
        0 to GreenhouseA(requiredSpecie),
        1 to GreenhouseB(requiredSpecie),
        2 to GreenhouseC(requiredSpecie)
    )
    private var genes: IntArray = IntArray(features.size)

    init {
        features.forEach { (t, u) ->
            genes[t] = if (u.isOne(inputData)) 1 else 0
        }
        calcFitness()
    }

    //Calculate fitness
    fun calcFitness() {
        fitness = 0
        for (i in genes.indices) {
            if (genes[i] == 1) {
                ++fitness
            }
        }
    }

    fun toStringColor(): String {
        //with colors
        var genesString = "[genes=["
        for ((increment, gene) in genes.withIndex()) {
            //print gene
            if (gene == 0) genesString += ConsoleColors.BLACK_BOLD + ConsoleColors.RED_BACKGROUND_BRIGHT + gene + ConsoleColors.RESET
            if (gene == 1) genesString += ConsoleColors.BLACK_BOLD + ConsoleColors.GREEN_BACKGROUND_BRIGHT + gene + ConsoleColors.RESET
            //print comma
            if (increment < genes.size - 1) genesString += ", "
        }
        genesString += "]]"
        genesString += " | [features= ${features.values.joinToString()}"
        return genesString
    }

    fun getGenes(): IntArray {
        return genes
    }

    fun setGenes(genes: IntArray) {
        genes.indices.forEach { i -> this.genes[i] = genes[i] }
    }

    override fun compareTo(other: Harvest): Int {
        return other.fitness - this.fitness
    }

    override fun toString(): String {
        //without colors
        return "[genes= ${genes.contentToString()}] | [features= ${features.values.joinToString { "," }}"
    }


}