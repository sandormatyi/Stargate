@echo off
set OLDDIR=%CD%

cd /d %~dp0

echo --- Cleaning project... ---
IF EXIST bin rmdir /s/q bin
mkdir bin

echo --- Compiling... ---
javac -encoding UTF-8 -d .\bin .\src\controller\*.java .\src\debug\*.java .\src\gamemodel\*.java .\src\gamemodel\events\*.java .\src\test\*.java .\src\userinterface\*.java

echo --- Copying resources... ---
robocopy ./res ./bin /E >nul

echo --- Compilation finished ---
echo.
chdir /d %OLDDIR%
pause