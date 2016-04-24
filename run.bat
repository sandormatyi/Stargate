@echo off
set OLDDIR=%CD%

cd /d %~dp0
cd .\bin

chcp 65001
java userinterface.Main

echo.
chdir /d %OLDDIR%