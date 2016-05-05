@echo off
set OLDDIR="%CD%"

cd /d "%~dp0"
cd .\bin 2>nul
if errorlevel==1 (
	echo Futtatas elott forditsd le a forraskodot!
	echo.
	pause
	goto :end)
	

chcp 65001 >nul
"%JAVA_HOME%/bin/java" userinterface.Main

:end
chdir /d "%OLDDIR%"
