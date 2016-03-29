set OLDDIR=%CD%

cd /d %~dp0
javac .\src\controller\*.java .\src\debug\*.java .\src\gamemodel\*.java .\src\gamemodel\events\*.java .\src\test\*.java .\src\userinterface\*.java

chdir /d %OLDDIR%