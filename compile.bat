@echo off
set OLDDIR=%CD%

cd /d %~dp0

javac -encoding UTF-8 .\src\controller\*.java .\src\debug\*.java .\src\gamemodel\*.java .\src\gamemodel\events\*.java .\src\test\*.java .\src\userinterface\*.java

echo.
echo --- Compilation finished ---
echo.
chdir /d %OLDDIR%
pause