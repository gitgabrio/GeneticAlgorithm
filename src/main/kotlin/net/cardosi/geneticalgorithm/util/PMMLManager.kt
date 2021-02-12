package net.cardosi.geneticalgorithm.util

import org.dmg.pmml.Model
import org.dmg.pmml.PMML
import org.dmg.pmml.regression.NumericPredictor
import org.dmg.pmml.regression.RegressionModel
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

val LOGGER : Logger = LoggerFactory.getLogger("PMMLManager")

val PMMLFILEMODELS: SortedMap<String, String> = sortedMapOf("Greenhouse_A" to "Greenhouse_A.pmml",
    "Greenhouse_B" to "Greenhouse_B.pmml",
    "Greenhouse_C" to "Greenhouse_C.pmml")
val MODELS = PMMLFILEMODELS.keys.toTypedArray()

// Here is where mutation happens, at pmml model level
fun getPmmlFileModels(): SortedMap<String, String> {
    val rn = Random()
    if (rn.nextInt() % 7 < 5) {
        val modelToChange = MODELS[nextInt(MODELS.size)]
        mutatePMMLFile(modelToChange, PMMLFILEMODELS[modelToChange] ?: throw IllegalAccessError("Missing $modelToChange in $PMMLFILEMODELS"))
    }
    return PMMLFILEMODELS
}

// MUTATION
private fun mutatePMMLFile(modelName: String, pmmlFileName: String)  {
    LOGGER.info("Mutating $modelName in $pmmlFileName")
    val pmmlFile: File = FileUtils.getFile(pmmlFileName)
    val fis = FileInputStream(pmmlFile)
    val pmmlModel: PMML = KiePMMLUtil.load(fis, pmmlFileName)
    fis.close()
    val model: Model = pmmlModel.models
        .find { modelName == it.modelName } ?: throw IllegalAccessError("Failed to find model $modelName in file ${pmmlFile.absolutePath}")
    mutateRegressionModel(model as RegressionModel)
    writePMML(pmmlModel, pmmlFile.absolutePath)
}

private fun mutateRegressionModel(toMutate: RegressionModel) {
    LOGGER.debug("Mutating $toMutate")
    val regressionTable = toMutate.regressionTables[nextInt(toMutate.regressionTables.size)]
    LOGGER.debug("Mutating $regressionTable")
    mutateNumericPredictor(regressionTable.numericPredictors[nextInt(regressionTable.numericPredictors.size)])
}

private fun mutateNumericPredictor(toMutate: NumericPredictor) {
    LOGGER.info("Mutating $toMutate: previous coefficient ${toMutate.coefficient}")
    val percentMutation : Double = nextInt(9, 11).toDouble() / 10.0
    val newCoefficient = toMutate.coefficient.toDouble() * percentMutation
    toMutate.coefficient = newCoefficient
    LOGGER.info("Mutating $toMutate: new coefficient ${toMutate.coefficient}")
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


