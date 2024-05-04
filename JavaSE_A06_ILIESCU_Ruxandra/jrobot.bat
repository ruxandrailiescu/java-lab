@echo off
set ROBOCODE_HOME="C:\robocode"

rem Compile your robot
javac -classpath %ROBOCODE_HOME%\libs\robocode.jar -d . JRobot.java

rem Run Robocode with your compiled robot
java -Xmx512M -Dsun.io.useCanonCaches=false -cp %ROBOCODE_HOME%\libs\robocode.jar robocode.Robocode -battle JRobot