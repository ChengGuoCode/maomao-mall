@echo off

cd /d D:\guocheng\maomao_mall\maomao-mall\config\script
start start.bat

choice /t 15 /d y /n >nul

cd /d D:\guocheng\maomao_mall\maomao-mall\core-goods\script
start start.bat

cd /d D:\guocheng\maomao_mall\maomao-mall\core-merchant\script
start start.bat

cd /d D:\guocheng\maomao_mall\maomao-mall\core-order\script
start start.bat

cd /d D:\guocheng\maomao_mall\maomao-mall\core-payment\script
start start.bat

cd /d D:\guocheng\maomao_mall\maomao-mall\core-user\script
start start.bat

cd /d D:\guocheng\maomao_mall\maomao-mall\service-app\script
start start.bat

exit