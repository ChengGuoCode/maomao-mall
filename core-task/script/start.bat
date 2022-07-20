@echo off

set curPath=%~dp0
set split=""
:begin
for /F "tokens=1,* delims=\" %%i in ("%curPath%") do (
	set split=%%i
)
for /F "tokens=1,* delims=\" %%i in ("%curPath%") do (
	set curPath=%%j
)
if not "%parentPath%"=="" goto gotJpdaOpts
set parentPath=%split%\
goto begin
:gotJpdaOpts
if %parentPath%%split%\==%~dp0 goto end
set parentPath=%parentPath%%split%\
goto begin
:end

java -jar %parentPath%build\libs\core-task-1.0-SNAPSHOT.jar

exit