package fr.dieul.lab.geneticalgorithm.model

import fr.dieul.lab.geneticalgorithm.util.ConsoleColors
import java.util.*
import kotlin.math.abs
import kotlin.Comparable as Comparable1


//Individual class
class Individual(geneLength: Int): Comparable1<Individual> {

    var fitness = 0
    private var genes: IntArray = IntArray(geneLength)

    init {
        //Initialization
        val rn = Random()
        //Set genes randomly for each individual
        for (i in genes.indices) {
            genes[i] = abs(rn.nextInt() % 2)
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
        return "[genes= ${genes.contentToString()}]"
    }


}