package net.cardosi.geneticalgorithm.features

import java.util.Random

class HairLengthFeature : AbstractFeature() {

    init {
        featureVal= Random().nextInt(70)
    }

    override fun isOne(): Boolean {
        return featureVal as Int > 20
    }





}