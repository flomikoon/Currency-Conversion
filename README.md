CI for 'main' branch:  [![Java CI with Maven](https://github.com/flomikoon/Currency-Conversion/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/flomikoon/Currency-Conversion/actions/workflows/maven.yml)

CI for 'develop' branch: [![Java CI with Maven](https://github.com/flomikoon/Currency-Conversion/actions/workflows/maven.yml/badge.svg?branch=develop)](https://github.com/flomikoon/Currency-Conversion/actions/workflows/maven.yml)


# Currency-Conversion

Программа представляет собой консольное приложение для конвертации валют.

Как приложение работает:

Сначало вводим валюту из которой хотим ковертировать , потом воддим валюту в которую хотим конвертировать

И в 3 строке вводим сумму которую хотим ковертировать.

![logo](https://i.ibb.co/fMJXWL3/image.png)

Для запуска приложения необходимо скачать проект к себе

Выполнить команду:

docker build -t currency-conversion .

После чего выполнить команду:

docker run -it currency-conversion
