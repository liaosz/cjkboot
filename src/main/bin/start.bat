@title ´æ½¡¿µ½Ó¿Ú³ÌÐò
@echo off
@REM *************************************************************************
@REM This script is used to start cjk_interface systems.
@REM *************************************************************************
@REM Set jvm options
@set JAVA_OPTIONS=-server -Duser.dir=%~dp0 -XX:-UseGCOverheadLimit -Dloader.path=.\lib,.\config
for %%i in (.\lib\cjkboot*.jar) do (set JARNAME=%%i)
@set JRE_HOME=./jre1.8.0_291
@set CLASSPATH=%JRE_HOME%\lib\rt.jar;
@set PATH=%JRE_HOME%\bin;
java -version
java %JAVA_OPTIONS% -jar %JARNAME%
pause
