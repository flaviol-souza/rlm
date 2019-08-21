## IMPLEMENTAÇÃO DE UM ALGORITMO DE LOCALIZAÇÃO DE ROBÔS MÓVEIS
###### IMPLEMENTATION OF A THE LOCATION ALGORITHM TO MOBILE ROBOT
#### Trabalho de Conclusão de Curso (TCC) de [Flavio Souza](https://flaviosouza.net) apresentado a UNIARA para o curso de Engenharia de Computação em 2012 (Orientador: Prof. Dr. Rodrigo Elias Bianchi)

Este é um projeto que implementa o algoritmo de Monte Carlo para localização de robôs moveis (NXT), utilizando um sonar. Os dados de mapeamento e localização são transmitidos, por bluetooth, a um computador que processa e renderiza os dados em um mapa. [Video ilustrativo.](https://www.youtube.com/watch?v=IEy9Dxg0TRc) 

[![Alt text](https://img.youtube.com/vi/IEy9Dxg0TRc/0.jpg)](https://www.youtube.com/watch?v=IEy9Dxg0TRc)

## More Details
Este projeto utilizou de kit Lego NXT 2.0, seu template de construção física esta detalhada em [http://nxtprograms.com](http://nxtprograms.com/NXT2/explorer/index.html) que utilizou dos recursos nativos do kit. 
O firware utilizado foi o leJOS, que dá suporte a linguagem Java, conforme o código disponibilizado. Com a leitura do ambiente, por um sonar, os dados são transmitidos, via bluetooth, para um computador que processa os dados e o rederiza-o. Neste modulo, do computador, também foi desenvolvido em Java.

![alt text](https://github.com/flaviol-souza/rlm/blob/master/images/modulos.png)

O robô faz apenas faz as aquisições dos dados, o processamento e a interpretação é feita no computador. Na ocasião, o NXT, não apresentava boa performance em multithreads. Com a interpretação dos dados, o computador passa sinais de controle ao robô. Mesmo em um formato modular, esta solução apresenta caracteristas autônoma. O resultado do passo a passo de progreção e da convergência de localização por monte carlo é apresentado:

![alt text](https://github.com/flaviol-souza/rlm/blob/master/images/monte-carlo.png)

> Os detalhes, na integra, do projeto podem ser conferidos na [dissertação do trabalho](https://github.com/flaviol-souza/rlm/blob/master/tcc-flavio-souza-eng-computacao.pdf)

## Build With
* Java
* NXT 2.0

## Acknowledgments
Agradeço imensamente ao meu pai, meu primeiro professor, que sempre me inspirou a buscar novos desafios.

_A special acknowledgment to [Piotr Czekalski](http://www.piotrczekalski.pl) that helped me, via messages at 2012, to clarify some of the Implementation issues about my project. Wow, Piotr Czekalski, you are amazing. tks man!_

Agradeço também ao Rodrigo Bianchi e ao Rodrigo Malara, que me apoiaram muito no devenvolver deste trabalho.

## Author
* Flavio Souza [contatos](https://www.flaviosouza.net)
    * Rodrigo Bianchi _Orientador_ [mailto](mailto:bianchi@ifsp.edu.br)

## License
[MIT](https://choosealicense.com/licenses/mit/)
Permission is hereby granted, free of charge, to any person obtaining a copy of this solution to deal with the publication, use or customization of the Software without restriction to whom it is provided, subject to the following conditions:

The notice of Reaction Team and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION WARRANTIES OF MERCHANTABILITY FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
