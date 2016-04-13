@echo off
set OLDDIR=%CD%

cd /d %~dp0
cd .\src

chcp 65001
java userinterface.Main

chdir /d %OLDDIR%