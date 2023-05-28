@echo off

@rem 读取程序版本
set /p a=<FunGame.java

@rem 创建版本日志
if not exist "FunGame.log" (
	echo %a%>FunGame.log
	javac -encoding utf-8 FunGame.java
)

@rem 读取版本日志
set /p b=<FunGame.log

@rem 版本是否相同
if not "%a%"=="%b%" (
	@rem 覆盖版本日志
	echo %a%>FunGame.log
	javac -encoding utf-8 FunGame.java
)

java FunGame

pause