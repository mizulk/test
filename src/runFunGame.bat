@echo off

@rem 读取程序版本
set /p game_version=<FunGame.java

@rem 创建版本日志
if not exist "FunGame.log" (
	echo %game_version%>FunGame.log
	javac -encoding utf-8 FunGame.java
)

@rem 读取版本日志
set /p log_version=<FunGame.log

@rem 版本是否相同
if not "%game_version%"=="%log_version%" (
	@rem 覆盖版本日志
	echo %game_version%>FunGame.log
	javac -encoding utf-8 FunGame.java
)

java FunGame

pause