@ECHO OFF
SET OLDDIR=%CD%
SET ERRORLEVEL=

cd /d %~dp0

ECHO --- Cleaning project... ---
IF EXIST bin rmdir /s/q bin
mkdir bin

ECHO --- Compiling... ---
SET "sources=src\controller\*.java src\controller\events\*.java src\debug\*.java src\gamemodel\*.java src\gamemodel\events\*.java src\userinterface\*.java src\userinterface\containers\*.java src\userinterface\elements\*.java src\userinterface\viewobjects\*.java"

javac -encoding UTF-8 -d .\bin %sources% 2>nul

IF ERRORLEVEL==1 (
	ECHO.
	ECHO A 'javac.exe' eleresi utvonala hianyzik a PATH kornyezeti valtozobol!
	ECHO Ujraprobalkozom a 'JAVA_HOME'-mal...
	ECHO.
	"%JAVA_HOME%/bin/javac" -encoding UTF-8 -d .\bin %sources% 2>nul

	IF ERRORLEVEL==2 (
		ECHO.
		ECHO A 'JAVA_HOME' kornyezeti valtozo sincsen beallitva!
		ECHO Meg egy utolso probalkozas...
		ECHO.

		FOR /f %%j in ("javac.exe") DO (
    			SET JAVA_HOME=%%~dp$PATH:j
		)

		IF "%JAVA_HOME%."=="." (
			ECHO.
   			ECHO Ez sem sikerult!
			ECHO Kerlek, add hozza kezzel a 'javac.exe'-t tartalmazo konyvtar eleresi utvonalat a PATH-hoz vagy allitsd be a 'JAVA_HOME' kornyezeti valtozot es probalkozz ujra a forditassal!
			ECHO.
			GOTO :error
		)

		"%JAVA_HOME%/bin/javac" -encoding UTF-8 -d .\bin %sources% 2>nul

		IF ERRORLEVEL==3 (
			ECHO.
   			ECHO Ez sem sikerult!
			ECHO Kerlek, add hozza kezzel a 'javac.exe'-t tartalmazo konyvtar eleresi utvonalat a PATH-hoz vagy allitsd be a 'JAVA_HOME' kornyezeti valtozot es probalkozz ujra a forditassal!
			ECHO.
			GOTO :error
		)
	)
)

ECHO --- Copying resources... ---
robocopy ./res ./bin /E >nul

ECHO --- Compilation successful ---
ECHO.
GOTO :end

:ERROR
rmdir /s/q bin

:end
chdir /d "%OLDDIR%"
PAUSE

EXIT /b %ERRORLEVEL%
