call javac server\*.java
call javac client\*.java
call javac compute\*.java
call jar cvf classes\compute.jar compute\*.class
call javac -cp classes\compute.jar engine\Executor.java
call javac -cp classes\compute.jar server\services\*.java server\tasks\*.java
pause