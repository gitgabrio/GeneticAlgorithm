package net.cardosi.geneticalgorithm.features

import java.util.Random

class EyesFeature : AbstractFeature() {

    private val colors: Array<String>  = arrayOf("BLACK", "BROWN", "GREEN", "BLUE")

    init {
        featureVal= colors[Random().nextInt(colors.size)]
    }


    override fun isOne(): Boolean {
        return featureVal as String == "BLACK" || featureVal as String == "BROWN"
    }





}