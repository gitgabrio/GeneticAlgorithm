package net.cardosi.geneticalgorithm.features

import java.util.Random

class SkinColorFeature : AbstractFeature() {

    private val colors: Array<String>  = arrayOf("BLACK", "WHITE")
    init {
        featureVal = colors[Random().nextInt(colors.size)]
    }

    override fun isOne(): Boolean {
        return featureVal as String == "BLACK"
    }


}