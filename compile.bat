@echo off
set OLDDIR=%CD%

cd /d %~dp0

IF NOT EXIST bin mkdir bin
javac -encoding UTF-8 -d .\bin .\src\controller\*.java .\src\debug\*.java .\src\gamemodel\*.java .\src\gamemodel\events\*.java .\src\test\*.java .\src\userinterface\*.java

IF NOT EXIST bin/res mkdir bin/res
robocopy ./res ./bin /E

echo.
echo --- Compilation finished ---
echo.
chdir /d %OLDDIR%
pause