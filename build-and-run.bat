@echo off
cd "Source Code\src"
"C:\Program Files\Java\jdk-17\bin\javac.exe" -encoding UTF-8 -d ..\bin -cp . *.java KComponent\*.java Levels\*.java System\*.java UI\*.java >nul 2>&1
cd ..\bin
"C:\Program Files\Java\jdk-17\bin\jar.exe" cvfe MouzMaze.jar RunMouzMaze . >nul 2>&1
start "" "C:\Program Files\Java\jdk-17\bin\javaw.exe" -jar MouzMaze.jar
