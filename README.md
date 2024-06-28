client call structure:
java -cp . client.Client <service_name> <service_method> <service_parameters_1> ... <service_parameters_1>

client call example:
java -cp . client.Client cpf generate
java -cp . client.Client cpf validate "123.456.789-09"
java -cp . client.Client lorem generate
java -cp . client.Client lorem generate 200 6 15 true
java -cp . client.Client cipher encrypt "boa noite professor" "cdp"
java -cp . client.Client cipher encrypt "drp prxvh etrugvhqu" "cdp"
java -cp . client.Client randomColor generate 