cd "Source Code\src"
"C:\Program Files\Java\jdk-17\bin\javac.exe" -encoding UTF-8 -d ..\bin -cp . *.java KComponent\*.java Levels\*.java System\*.java UI\*.java
cd ..\bin
"C:\Program Files\Java\jdk-17\bin\jar.exe" cvfe MouzMaze.jar RunMouzMaze .
"C:\Program Files\Java\jdk-17\bin\java.exe" -jar MouzMaze.jar