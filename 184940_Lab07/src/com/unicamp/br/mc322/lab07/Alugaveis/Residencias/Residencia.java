package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

abstract class Residencia extends Alugavel{
	//Essa classe tem como inten��o definir atributos padr�es para os diferentes tipos de resid�ncias/locais alug�veis. Ela herda de Alug�veis os atributos e m�todos padr�es para os produtos do sistema.
	
	protected String nome;//Toda local alug�vel tem um nome
	Residencia(String id, double valor, Point endereco, String nome){//Construtor padr�o para a classe, o qual seta seus atributos originais e herdados
		super(id,valor,endereco);
		this.nome=nome;
	}
}
