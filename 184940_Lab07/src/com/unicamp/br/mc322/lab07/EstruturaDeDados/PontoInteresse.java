package com.unicamp.br.mc322.lab07.EstruturaDeDados;

public class PontoInteresse {
	//Classe usada como estrutura de dados para o sistema
	private String nome;//Quarda o nome do ponto de interesse
	private Point endereco;//Quarada o endere�o do ponto de interesse
	
	public PontoInteresse(String nome, Point endereco){
		this.nome=nome;
		this.endereco=endereco;
	}
	
	public Point getPoint() {//Getter para o endere�o
		return endereco;
	}
	
	public String getNome(){//Getter para o nome
		return nome;
	}
}
