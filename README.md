# maximums-samples-v2

#Add the below build scripts just above the plugins to run the spring boot application using bootRun
```json
buildscript {
    ext {
        springBootVersion = '2.4.0'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(
                "org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}
```

#Add org.springframework.boot in plugins
```json
plugins {
    id 'java'
    id "org.springframework.boot" version "2.4.0"
}
```

#Run the spring boot application 
<br/>
gradle bootRun

----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------

#Add the below task in build.gradle
```json
task wrapper(type: Wrapper) {
    gradleVersion = '6.8.1'
}
```

#Run the below command to generate the gradlew file
<br/>
gradle wrapper

#Run the spring boot application
<br/>
gradlew bootRun