package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

abstract class Residencia extends Alugavel{
	//Essa classe tem como intenção definir atributos padrões para os diferentes tipos de residências/locais alugáveis. Ela herda de Alugáveis os atributos e métodos padrões para os produtos do sistema.
	
	protected String nome;//Toda local alugável tem um nome
	Residencia(String id, double valor, Point endereco, String nome){//Construtor padrão para a classe, o qual seta seus atributos originais e herdados
		super(id,valor,endereco);
		this.nome=nome;
	}
}
