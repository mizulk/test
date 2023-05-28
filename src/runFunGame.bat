@echo off

if not exist "FunGame.class" (
	javac -encoding utf-8 FunGame.java
)

java FunGame

pause