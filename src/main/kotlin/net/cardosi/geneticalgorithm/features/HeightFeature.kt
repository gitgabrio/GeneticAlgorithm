package net.cardosi.geneticalgorithm.features

import java.util.Random

class HeightFeature : AbstractFeature() {

    init {
        featureVal = Random().nextInt(70) + 150
    }

    override fun isOne(): Boolean {
        return featureVal as Int > 175
    }


}