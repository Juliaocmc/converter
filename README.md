# Converter para Sistema Internacional de Unidades (SI)
Este projeto tem a função de converter unidades de medidas para sua SI. 
Ex: Horas (h) para segundos (s)

O projeto é escrito com a tecnologia Spring Boot (Java), é utilizado um banco relacional (Postgres) e é dockerizado. 
Para sua utilização, é necessário que primeiramente clonar este repositório e acessar sua pasta raiz  <code>../converter</code>.

O pacote já possui uma imagem Docker do projeto e a relação desta imagem com o banco de dados será realizada pelo docker-compose, pelo comando <code> docker-compose up</code>. Então o codigo será copilado, rodará na porta 8080 e banco de dados expoem a porta 5432. É esperado também que seja criado a tabela (unit_conversion_factors). Caso não deseje observar os logs e utilizar o mesmo terminal, utilize o comando <code> docker-compose up -d</code>. 

Agora é necessário inserir os dados no banco de dados. Você pode fazer isto pelo seu administrador de banco favórito, ou se não houver um interface grafica, basta utilizar o comando <code>docker exec -it postgres psql -U userConvert  convertdb</code> para inserir os dados na tabela criada. Caso solicite, a senha é (convert123).

Os dados a serem inseridos estão presente no seguinte comando SQL:<br />
```
INSERT INTO public.unit_conversion_factors
(id, "name", symbol, quantity, si_conversion, si)
VALUES('1380d200-702f-4f43-a7b2-211c59e6ff8e', 'minute', 'min', 'time', 60, 's'),
('637339ac-e906-45f1-855a-2795d2dae1fe', 'hour', 'h', 'time', 3600, 's'),
('47dcb583-a4e8-4921-a47d-64d020702e51', 'day', 'd', 'time', 86400, 's'),
('16177fce-360a-4282-94ba-d0d2de6521bc', 'degree', '°', 'unitless/plane angle', 0.01745329251994329576923691, 'rad'),
('38132c76-5ca6-41e7-9a2b-a92e3fa9e970', 'arcminute', '''', 'unitless/plane angle', 0.00029088820866572159615395, 'rad'),
('76803a24-5a29-4f98-bf73-9fab2c3e62b1', 'arcsecond', '"', 'unitless/plane angle', 0.0000048481368110953599359, 'rad'),
('0bfdc263-c8a2-432b-9b59-7b7635bc852b', 'hectare', 'ha', 'area', 10000, 'm²'),
('84cc0510-2aad-4229-884d-54138ae4ab5e', 'litre', 'L', 'volume', 0.001, 'm³'),
('2c55ee4a-4e84-4c06-a833-a0cbf5e9fcc3', 'tonne', 't', 'mass', 1000, 'kg');
```

Acesse pelo seu browser ou sua API client (Postman, Insominia, etc) o endereço <code>localhost:8080/units?unitString=</code>.

Os parametros (que vamos chamar de unidades) a serem passados, estão presentes na tabela abaixo:

| Name      	| Symbol 	|
|-----------	|--------	|
| minute    	| min    	|
| hour      	| h      	|
| day       	| d      	|
| degree    	| °      	|
| arcminute 	| '      	|
| arcsecond 	| "      	|
| hectare   	| ha     	|
| litre     	| L      	|
| tonne     	| t      	|

As unidades pode ser somadas <code>+</code>, subtraidas <code>-</code>, multiplicadas <code>*</code> ou divididas <code>/</code>.<br/>
Podem também estarem envolvidas por parênteses <code>()</code>.

Exemplo 1 : <code>localhost:8080/units?unitString=***day/minute***</code><br/>
Exemplo 2 : <code>localhost:8080/units?unitString=***hectare*(litre/minute)***</code>


O retorno, é um Json (que será chamado de objeto de conversão) contendo duas informações, a unitName, que é a equação convertida para as si correspondes as unidades informadas, e o multiplicationFactor, que é o fator de conversão das unidades. 

Exemplo 1 : 
```
GET localhost:8080/units?unitString=***day/minute***


{
  "unitName": "s/s",
  "multiplicationFactor": 1440.0
}
```

Exemplo 2 : 
```
GET localhost:8080/units?unitString=***hectare*(litre/minute)***


{
  "unitName": "m²*(m³/s)",
  "multiplicationFactor": 0.16666666666666669
}


