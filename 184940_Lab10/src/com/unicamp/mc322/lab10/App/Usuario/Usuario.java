package com.unicamp.mc322.lab10.App.Usuario;

import com.unicamp.mc322.lab10.App.Date.Date;

public class Usuario {
	//Essa classe representa um usu�rio do aplicativo, segundo o enunciado, cada usu�rio tem um cpf, um nome, um cart�o de cr�dito e uma data de nascimento
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
