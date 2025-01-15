Title
===
Youtube Transcript Fetching tool

# Papar Information
- Authors: @anshumann548 , NinzaRJ01

## Install & Dependence
- Maven (Optional)
- RestAssured
- Java 17
- TestNg


### Use
- for fetching transcript from youtube videos
  ```

#### Directory Hierarchy
```
|—— transcript-fetch
|    |—— pom.xml
|    |—— src
|        |—— main
|            |—— java
|                |—— com
|                    |—— transcript
|                        |—— fetch
|                            |—— YouTubeTranscriptFetcher.java
|                            |—— paramcatcher.java
|        |—— test
|            |—— java
|                |—— com
|                    |—— transcript
|                        |—— fetch
|                            |—— YouTubeTranscript.java
|    |—— target
|        |—— classes
|            |—— com
|                |—— transcript
|                    |—— fetch
|                        |—— YouTubeTranscriptFetcher$1.class
|                        |—— YouTubeTranscriptFetcher.class
|                        |—— paramcatcher.class
|        |—— generated-sources
|            |—— annotations
|        |—— generated-test-sources
|            |—— test-annotations
|        |—— maven-archiver
|            |—— pom.properties
|        |—— maven-status
|            |—— maven-compiler-plugin
|                |—— compile
|                    |—— default-compile
|                        |—— createdFiles.lst
|                        |—— inputFiles.lst
|                |—— testCompile
|                    |—— default-testCompile
|                        |—— createdFiles.lst
|                        |—— inputFiles.lst
|        |—— surefire-reports
|        |—— test-classes
|            |—— com
|                |—— transcript
|                    |—— fetch
|                        |—— YouTubeTranscript$1.class
|                        |—— YouTubeTranscript.class
|        |—— transcript-fetch-1.0-SNAPSHOT.jar
```

#### How to use 
```
1.Clone the repository
2.Run the test class
3.Enter YT video query param value for e.g. "FpB9fBg9ZJk" form url any url for e.g. "https://www.youtube.com/watch?v=FpB9fBg9ZJk"
4.It will provide transcript if available

