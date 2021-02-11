package net.cardosi.geneticalgorithm.model

import net.cardosi.geneticalgorithm.features.*
import net.cardosi.geneticalgorithm.util.ConsoleColors
import kotlin.Comparable as Comparable1


//Individual class
class Individual() : Comparable1<Individual> {

    var fitness = 0
    private val features: Map<Int, AbstractFeature> = mapOf(
        0 to EyesFeature(),
        1 to HairColorFeature(),
        2 to HairLengthFeature(),
        3 to HeightFeature(),
        4 to SkinColorFeature(),
        5 to WeightFeature()
    )
    private var genes: IntArray = IntArray(features.size)

    init {
        features.forEach { (t, u) ->
            genes[t] = if (u.isOne()) 1 else 0
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

    override fun compareTo(other: Individual): Int {
        return other.fitness - this.fitness
    }

    override fun toString(): String {
        //without colors
        return "[genes= ${genes.contentToString()}] | [features= ${features.values.joinToString { "," }}"
    }


}