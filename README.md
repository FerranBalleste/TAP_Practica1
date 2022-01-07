# TAP_Practica1
06/01/2022
Ferran Balleste Solsona: ferran.balleste@estudiants.urv.cat

<p>The goal is to design and develop a DataFrame library in Java using Design Patterns.</p>
<p>DataFrames are tables of data where rows are items and columns are labeled traits. Each row is identified by 
an item id (usually an integer) and each column is identified by a label name (usually string). Columns and rows 
can thus be specified by using these identifiers, instead of the table integer coordinates.</p>

## Data Files + Factory
<p>We want to enable the program to load data files of different types: .csv, .txt and json. To achieve it, we have used the factory pattern</p>
<p>Click [here](https://github.com/FerranBalleste/TAP_Practica1/tree/master/src/dataframe)</p>
<p>The diffent classes do not implement the code directly, they only implement the lecture of the file. A coomon implementation for every type has been created in:
(https://github.com/FerranBalleste/TAP_Practica1/tree/master/src/table)
<p>Each data type inside the table is a TableElement to avoid bad smells and the use of instanceof inside the code (you can extend the interface if you need new ones):
(https://github.com/FerranBalleste/TAP_Practica1/blob/master/src/table/element/TableElement.java)</p>

## Composite
<p>To handle different dataframes at the same time we have implemented a composite pattern in: https://github.com/FerranBalleste/TAP_Practica1/tree/master/src/dataframe/composite</p>
<p> A composite DataFrame has the same operations as normal DataFrames. In the composite, nodes are 
directories and leaves, files. Nodes and leaves also act as DataFrames. DataFrame operations can be executed 
at any level of the composite </p>
<p>Certain functions might not work if the different labels aren't the same.</p>

## MapReduce
<p>We have added a mapreduce function on the composite that works on single columns with the same name.</p>
<p>As a function it takes a TableElement BinaryOperator, there are several inside TableElement interface (add, min, max)</p>

## Visitor
<p>A visitor pattern has been added to the previous composite structure. The visitor must allow to run new operations on 
all DataFrames in the composite (recursively through the directories).</p>
<p>Maximum, minimum, average and sum visitors have been implemented: (https://github.com/FerranBalleste/TAP_Practica1/tree/master/src/dataframe/visitors)

