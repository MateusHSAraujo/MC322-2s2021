package com.unicamp.mc322.lab10.App.Usuario;

import com.unicamp.mc322.lab10.App.Date.Date;

public class Usuario {
	//Essa classe representa um usuário do aplicativo, segundo o enunciado, cada usuário tem um cpf, um nome, um cartão de crédito e uma data de nascimento
	protected String nome;
	protected String cpf;
	protected String cartaoDeCredito;
	protected Date nascimento;
	
	public Usuario(String nome,String cpf, String cartao, Date aniversario) {
		this.nome=nome;
		this.cpf=cpf;
		cartaoDeCredito=cartao;
		nascimento=aniversario;
	}
	
	
}
