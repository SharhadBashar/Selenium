@ECHO OFF
ECHO Starting Performance Testing
SET /P qmw=Please enter the QMW Site: 
java -jar libtimingSOP.jar %qmw% "%CD%\\Drivers\\IEDriverServer.exe" %CD%
timeout /t 5 /nobreak > NUL
java -jar libtimingTMM.jar %qmw% "%CD%\\Drivers\\IEDriverServer.exe" %CD%
timeout /t 5 /nobreak > NUL
java -jar jobTitlesReport.jar %qmw% "%CD%\\Drivers\\IEDriverServer.exe" %CD%
timeout /t 5 /nobreak > NUL
java -jar trainingRequestReport.jar %qmw% "%CD%\\Drivers\\IEDriverServer.exe" %CD%
timeout /t 5 /nobreak > NUL
java -jar trainingOverviewReport.jar %qmw% "%CD%\\Drivers\\IEDriverServer.exe" %CD%
ECHO Done Performance Testing!!!
pause