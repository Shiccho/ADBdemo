
# Final Report ADB Team Ganbarimasu

This is our application using Scaladb in ADB class.

## Prerequires

Change current directory to getting-started.
```Bash
$ cd docs/getting-started
```

Please modify "scalardb.propaties" depending on your own environment and put the schema-loader.


## Run

First, define the schema by doing the following command:
```Bash
$ java -jar scalardb-schema-loader-<version>.jar --config scalardb.properties -f sample.json --coordinator
```

Next, please set main class "sample.PreExec" in "build.gradle".
```Bash
mainClassName = "sample.PreExec"
```

Then, create traffic information by doing the following command:
```Bash
$ gradlew clean build run
```
You can define your own route by modify "PreExec.java"

Please change the mainClassName from "sample.PreExec" to "sample.MaaSMain" in "build.gradle".<br>
Then you can start our application runs by doing the following command:
```Bash
$ gradlew clean build run 
```
Please open the following URL on your blowser
```URL
http://localhost:7070/
```

Please use web application by following instruction on presentation slide.

## Note
I think there is a bug caused by the route search algorithm, what makes the program enter an infinite loop, if there is a cycle in the route.