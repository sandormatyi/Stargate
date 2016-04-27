@echo off
set OLDDIR=%CD%

cd /d %~dp0
cd .\bin 2>nul
if errorlevel==1 (
	echo Futtatas elott forditsd le a forraskodot!
	echo.
	pause
	goto :end)
	

chcp 65001 >nul
echo Ha Command Promptbol futtatod a szkriptet, kerlek válts Lucida Console betutipusra!
echo.
pause
echo.
echo Windows 7 és korabbi rendszereken a Command Prompt Unicode támogatasanak hianya miatt a kepernyore kiirt kimenet hibas lehet (ekezetet tartalmazo sorok vegen a sorvegi karakterek megismetlodnek). Ez azonban nem erinti a java altal irt és olvasott szoveget, igy a tesztek validalasat sem.
echo.
pause
echo.
"%JAVA_HOME%/bin/java" userinterface.Main

echo.

:end
chdir /d %OLDDIR%
