package net.cardosi.geneticalgorithm.features

import net.cardosi.geneticalgorithm.util.getPMMLRequestData
import net.cardosi.geneticalgorithm.util.getPMMLRuntime
import org.kie.api.pmml.PMML4Result
import org.kie.api.pmml.PMMLRequestData
import org.kie.pmml.api.runtime.PMMLContext
import org.kie.pmml.api.runtime.PMMLRuntime
import org.kie.pmml.evaluator.core.PMMLContextImpl

abstract class AbstractGreenhouse(private val requiredSpecie: String,
                                  private val pmmlFile: String,
                                  private val modelName: String) {


    private val pmmlRuntime: PMMLRuntime = getPMMLRuntime(pmmlFile);

    fun isOne(inputData: Map<String, Any>): Boolean {
        return evaluateModel(inputData) == requiredSpecie
    }

    override fun toString(): String {
        return "${this.javaClass.simpleName}: $pmmlFile"
    }

    private fun evaluateModel(inputData: Map<String, Any>): String {
        val pmmlRequestData: PMMLRequestData = getPMMLRequestData(modelName, inputData)
        val pmmlContext: PMMLContext = PMMLContextImpl(pmmlRequestData)
        val pmml4Result: PMML4Result = pmmlRuntime.evaluate(modelName, pmmlContext)
        return pmml4Result.resultVariables[pmml4Result.resultObjectName] as String;
    }

}