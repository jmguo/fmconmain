We developed this project based on FeatureIDE (http://wwwiti.cs.uni-magdeburg.de/iti_db/research/featureide/). The development environment is Java SE 6, Eclipse RCP 3.5.2.
The main components are as follows:
1. Random feature models generator;
2. Random feature-model operations generator;
3. Consistency constraints of feature models;
4. Dependency matrix and evolution strategies for feature-model operations.
5. Five experiments: validating generated feature models using SAT4J and GUIDSL, measuring the computing time for different sizes of feature models, different kinds of operations, different number of operations, different kinds of evolution strategies. Experiments are performed by running the JUnit Test cases.