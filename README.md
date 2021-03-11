# GeneticAlgorithm
This is a fork/extension of the original [GeneticAlgorithm](https://github.com/memento/GeneticAlgorithm).
Many thanks to Ms Vijini Mallawaarachchi ( [@Vini2](https://github.com/Vini2) ) and [@memento](https://github.com/memento) for their work.

While the original one was meant to be as easy as possible to be used by datascience reader, this one is targeting developers, and will use generally
agreed upon standards (maven management, external libraries, etc)

This repo focuses on :  
- Kotlin/Functional refactoring
- using it in almost-real use case


Greenhouses example
===================
In this example there is a guy who decide to become a farmer specialized in apartment-plants.
When he creates his startup, he gathers information about the most requested plant ("fern", "rose" or "cactus") and environment variables
(hours of light and amount of water) of the place where he will build his farm.
Before entering in business, though, he must be sure to obtain the most possible profits, so all the greenhouses must be able to produce the required plant.
At the same time, the going-to-be-a-farmer is wise enough to know that environment variables may change, as like customer tastes for plants, so he think from day 0 to a 
setup that may be adjusted over time to reflect such changes.
He then builds three greenhouses.
Each greenhouse may produce "fern", "rose" or "cactus", depending on available light and water.
He builds the greenhouses with a slightly different setup, so that each one favors one of the three cultivated species.
Every week there are five harvest from each greenhouse (those are very extraordinary greenhouses, actually), and after each harvest the farmer modify slightly the greenhouses' setup to increase their probability of produce the required plant. 
When all the greenhouses produce the required plant on one harvest, the farmer has obtained the needed setup, and will put is farm in business.

Code representation
-------------------

In this example, harvests represents the population, and every harvest has a "genoma" with three genes, each of which is represented by a greenhouse.
The harvest fitness represents how many greenhouses are producing the required plant.
Every greenhouse is the execution of a PMML file/model.
The three greenhouses are mapping _Greenhouse_A.pmml_, _Greenhouse_B.pmml_ and _Greenhouse_C.pmml_, respectively.
Each of those PMMLs defines a _Regression_ model with three _RegressionTables_, each of which targeting a plant specie.

`<RegressionModel functionName="classification" modelName="..." targetFieldName="Species">
    <RegressionTable targetCategory="fern" ..,>
        ...
    </RegressionTable>
    <RegressionTable targetCategory="rose" ..,>
        ...
    </RegressionTable>
    <RegressionTable targetCategory="cactus" ..,>
        ...
    </RegressionTable>
</RegressionModel>`


Each table has a different intercept value, and inside each of them a different coefficient is given to _water_ and _light_ variables

`<RegressionTable targetCategory="..." intercept="1">
    <NumericPredictor name="water" exponent="1" coefficient="..."/>
    <NumericPredictor name="light" exponent="1" coefficient="..."/>
</RegressionTable>`

When the greenhouse produces the required plant, its gene is 1, 0 otherwise.
The harvest fitness is the number of genes with "1" as value.
The cycle ends when the required fitness is obtained, and that happen when all greenhouses produce the required plant.

At each iteration

1) the PMMLs are slightly modified to increase the probability of the requested specie (_intercept_ value modification)
1) two new harvests are created
3) during harvest creation, the greenhouses' production is evaluated (PMML evaluation)
4) a gene switch is applied to the two harvests
5) the fittest harvest is inserted inside the harvests' population, replacing the previous fittest one
6) when the fitness of a harvest is 3, the cycle ends

At the end of the execution, modified PMMLs will be stored inside _target/classes_, and a comparison with the original ones (_src/main/resources_) will show the needed modifications.








