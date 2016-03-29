set OLDDIR=%CD%

cd /d %~dp0
cd .\src
java userinterface.Main

chdir /d %OLDDIR%