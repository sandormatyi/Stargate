@echo off
set OLDDIR=%CD%

cd /d %~dp0

echo --- Cleaning project... ---
IF EXIST bin rmdir /s/q bin
mkdir bin

set "sources=src\controller\*.java src\controller\events\*.java src\debug\*.java src\gamemodel\*.java src\gamemodel\events\*.java src\userinterface\*.java src\userinterface\containers\*.java src\userinterface\elements\*.java src\userinterface\viewobjects\*.java"

javac -encoding UTF-8 -d .\bin %sources%

if errorlevel==1 (
	echo A 'javac.exe' eleresi utvonala hianyzik a PATH kornyezeti valtozobol! Ujraprobalkozom a 'JAVA_HOME'-mal...
	set errorlevel=0
	echo.
	"%JAVA_HOME%/bin/javac" -encoding UTF-8 -d .\bin %sources%

	if errorlevel==1 (
		echo A 'JAVA_HOME' kornyezeti valtozo sincsen beallitva! Meg egy utolso probalkozas...
		set errorlevel=0
		echo.

		for %i in (javac.exe) do @%i -encoding UTF-8 -d .\bin %sources%   %~$PATH:i

		if errorlevel==1 (
			echo Ez sem sikerult, kerlek, add hozza kezzel a 'javac.exe'-t tartalmazo konyvtar eleresi utvonalat a PATH-hoz vagy allitsd be a 'JAVA_HOME' kornyezeti valtozot es probalkozz ujra a forditassal!
			rmdir /s/q bin
			goto :end
			)
		)
	)

echo --- Compiling... ---


echo --- Copying resources... ---
robocopy ./res ./bin /E >nul

echo --- Compilation finished ---
echo.

:end
chdir /d "%OLDDIR%"
pause
