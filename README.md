

## DESAFIO 1BRC - SPRING BATCH - MENTORIA +praTI

### Pré requisitos:
- Java 17
- Maven
- Spring boot
- Spring Batch

### - The One Billion Row Challenge (utilizando o Spring Batch para processamento).

#### "O Desafio de Um Bilhão de Linhas - Uma exploração divertida de quão rapidamente 1 bilhão de linhas de um arquivo de texto podem ser processadas com Java."

(Desafio disponível em https://github.com/gunnarmorling/1brc)

#### O desafio consiste em processar um arquivo de texto com 1 bilhão linhas no formato "Cidade;temperatura".
Ex: Lodwar;11.1

Wau;31.2

Moscow;-1.1

Seville;26.2

.
.
.

#### O retorno da aplicação deve trazer o nome de cada cidade, a temperatura mínima, a temperatura máxima e a média, no formato nomeDaCidade=TempMinima/TempMedia/TempMaxima.
Ex:

{Abha=-21.6/18.4/58.4, Abidjan=-14.6/23.9/62.4, Abéché=-4.1/30.8/65.7,........, Zürich=-26.0/10.8/47.6, Ürümqi=-26.9/11.05/49.0, İzmir=-17.9/18.4/54.7}

### Como executar:
- Colocar o arquivo measurements.txt dentro da pasta main/resources

 (arquivo disponível em: https://drive.google.com/file/d/1PULRG4r-9ZFMIa9eb1KhPBpKK2Bb00HO/view?usp=sharing )

 - Executar o comando:
```bash
mvn clean spring-boot:run
```
