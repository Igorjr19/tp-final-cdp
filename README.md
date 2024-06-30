# Autoria
### Autor: Ígor José Rodrigues
### Disciplina: Computação Distribuída e Paralela
### Professor: Dr. Milton Hirokazu Shimabukuro

---
# Instruções para uso
Para compilar as classes execute o arquivo `compile.bat`.
Para executar o trabalho, você deve substituir a penúltima linha do arquivo `run.bat` (linha anterior ao comando `pause`) para executar o serviço desejado.

client call structure:
```
java -cp . client.Client <service_name> <service_method> <service_parameters_1> ... <service_parameters_n>
```

client call example:
```
java -cp . client.Client cpf generate
java -cp . client.Client cpf validate "123.456.789-09"
java -cp . client.Client lorem generate
java -cp . client.Client lorem generate 200 6 15 true
java -cp . client.Client cipher encrypt "boa noite professor" "cdp"
java -cp . client.Client cipher encrypt "drp prxvh etrugvhqu" "cdp"
java -cp . client.Client randomColor generate 4 
```