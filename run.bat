start rmiregistry
timeout /t 1
start java -cp ./;./classes/compute.jar -Djava.rmi.server.codebase=file:./classes/compute.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server-win.policy engine.Executor localhost 1099
timeout /t 1
start java -cp . server.Server
timeout /t 1
call java -cp . client.Client lorem generate 500 6 15 true
pause