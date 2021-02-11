package net.cardosi.geneticalgorithm.features

import java.util.Random

class HairColorFeature : AbstractFeature() {

    private val colors: Array<String>  = arrayOf("BLACK", "BROWN", "BLOND")
    init {
        featureVal= colors[Random().nextInt(colors.size)]
    }

    override fun isOne(): Boolean {
        return featureVal as String == "BLACK" || featureVal as String == "BROWN"
    }


}