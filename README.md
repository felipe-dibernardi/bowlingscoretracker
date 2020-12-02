# Bowling Score Tracker

## Index

1. **Setup**
2. **Compile and Running instructions**

## 1. Setup

* Java 8+ JDK
* Maven 3.6.3
* JUnit 5.7.0
* AssertJ 3.18.0

## 2. Compile and Running Instructions

1. Clone project **git clone https://github.com/felipe-dibernardi/bowlingscoretracker.git**

2. Run **mvn clean package** at pom.xml level folder.

3. Unit tests will be run in the mvn package cycle.

4. To run integration tests use **mvn integration-test**.

5. To run the application go to target folder and run the following command: 
**java -jar bowling-score-tracker-1.0.0.jar ${filepath}**

6. Example files for several scenarios, including corner cases can be found in the **src/test/resources** folder
