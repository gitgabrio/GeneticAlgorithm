package net.cardosi.geneticalgorithm.features

import java.util.Random

class WeightFeature : AbstractFeature() {

    init {
        featureVal = Random().nextInt(70) + 60
    }

    override fun isOne(): Boolean {
        return featureVal as Int in 81..119
    }


}