
package net.cardosi.geneticalgorithm.util

import org.kie.api.pmml.PMMLRequestData
import org.kie.pmml.api.runtime.PMMLRuntime
import org.kie.pmml.evaluator.assembler.factories.PMMLRuntimeFactoryImpl
import org.kie.pmml.evaluator.core.utils.PMMLRequestDataBuilder
import org.kie.test.util.filesystem.FileUtils.getFile
import java.io.File

fun getPMMLRuntime(fileName: String): PMMLRuntime {
    val pmmlFile: File = getFile(fileName)
    return PMMLRuntimeFactoryImpl().getPMMLRuntimeFromFile(pmmlFile)
}


fun getPMMLRequestData(modelName: String, parameters: Map<String, Any>): PMMLRequestData {
    val correlationId = "CORRELATION_ID"
    val pmmlRequestDataBuilder = PMMLRequestDataBuilder(correlationId, modelName)
    for ((key, pValue) in parameters) {
        pmmlRequestDataBuilder.addParameter(key, pValue, pValue.javaClass)
    }
    return pmmlRequestDataBuilder.build()
}
