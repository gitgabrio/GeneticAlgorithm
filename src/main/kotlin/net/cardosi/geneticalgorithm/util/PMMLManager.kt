package net.cardosi.geneticalgorithm.util

import org.dmg.pmml.Model
import org.dmg.pmml.PMML
import org.dmg.pmml.regression.NumericPredictor
import org.dmg.pmml.regression.RegressionModel
import org.dmg.pmml.regression.RegressionTable
import org.jpmml.model.PMMLUtil
import org.kie.pmml.compiler.commons.utils.KiePMMLUtil
import org.kie.test.util.filesystem.FileUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.random.Random.Default.nextInt

val LOGGER: Logger = LoggerFactory.getLogger("PMMLManager")

val PMMLFILEMODELS: SortedMap<String, String> = sortedMapOf(
    "Greenhouse_A" to "Greenhouse_A.pmml",
    "Greenhouse_B" to "Greenhouse_B.pmml",
    "Greenhouse_C" to "Greenhouse_C.pmml"
)
val MODELS = PMMLFILEMODELS.keys.toTypedArray()

// Here is where mutation happens, at pmml model level
fun getPmmlFileModels(requiredSpecie: String): SortedMap<String, String> {
    val modelToChange = MODELS[nextInt(MODELS.size)]
    mutatePMMLFile(
        modelToChange,
        PMMLFILEMODELS[modelToChange] ?: throw IllegalAccessError("Missing $modelToChange in $PMMLFILEMODELS"),
        requiredSpecie
    )
    return PMMLFILEMODELS
}

// MUTATION
private fun mutatePMMLFile(modelName: String, pmmlFileName: String, requiredSpecie: String) {
    LOGGER.info("Mutating $modelName in $pmmlFileName")
    val pmmlFile: File = FileUtils.getFile(pmmlFileName)
    val fis = FileInputStream(pmmlFile)
    val pmmlModel: PMML = KiePMMLUtil.load(fis, pmmlFileName)
    fis.close()
    val model: Model = pmmlModel.models
        .find { modelName == it.modelName }
        ?: throw IllegalAccessError("Failed to find model $modelName in file ${pmmlFile.absolutePath}")
    mutateRegressionModel(model as RegressionModel, requiredSpecie)
    writePMML(pmmlModel, pmmlFile.absolutePath)
}

private fun mutateRegressionModel(toMutate: RegressionModel, requiredSpecie: String) {
    LOGGER.debug("Mutating $toMutate")
//    val regressionTable = toMutate.regressionTables[nextInt(toMutate.regressionTables.size)]
    val regressionTable : RegressionTable = toMutate.regressionTables.first { requiredSpecie == it.targetCategory }
    LOGGER.debug("Mutating $regressionTable")
    val percentMutation: Double = nextInt(3, 4).toDouble() / 10.0
    val originalIntercept: Number = regressionTable.intercept as Number
//    val delta = originalIntercept.toDouble() * percentMutation
    regressionTable.intercept = originalIntercept.toInt() + 1
//    when (requiredSpecie == regressionTable.targetCategory) {
//        true -> regressionTable.intercept = originalIntercept.toDouble() + delta
//        false -> regressionTable.intercept = originalIntercept.toDouble() - delta
//    }
}

private fun writePMML(toWrite: PMML, fullPath: String) {
    LOGGER.debug("Write $toWrite to $fullPath")
    val fileContent = getPMMLContent(toWrite)
    LOGGER.debug("File content $fileContent")
    File(fullPath).writeText(fileContent)
}

private fun getPMMLContent(source: PMML): String {
    val outputStream = ByteArrayOutputStream()
    PMMLUtil.marshal(source, outputStream)
    return String(outputStream.toByteArray())
}


