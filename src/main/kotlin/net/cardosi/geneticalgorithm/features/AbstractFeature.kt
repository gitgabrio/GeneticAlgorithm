package net.cardosi.geneticalgorithm.features

abstract class AbstractFeature {

    abstract fun isOne(): Boolean

    override fun toString(): String {
        return "${this.javaClass.simpleName}: $featureVal"
    }

    protected lateinit var featureVal :Any


}