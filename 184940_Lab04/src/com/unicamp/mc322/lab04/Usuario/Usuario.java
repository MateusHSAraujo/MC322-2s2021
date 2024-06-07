package com.unicamp.mc322.lab04.Usuario;

import com.unicamp.mc322.lab04.Operadores.*;


public class Usuario {
	private String nome;
	private String cpf;
	private Date aniversario;
	private int idade;//A intenção desse atributo é armazenar a idade de um usuário considerando a data atual para facilitar operações futuras
	private Point endereco;
	private boolean possuiReserva; //A intenção desse atributo é armazenar a informação de que o usuário possui ou não um agendamento pendente para facilitar futuras operações
	
	public Usuario(String nome, String cpf, Date aniversario,Point endereco) {
		//Esse método constrói um usuário recebendo seu nome, cpf, data de aniversário e endereço
		this.nome=nome;
		this.cpf=cpf;
		this.aniversario=aniversario;
		this.endereco=endereco;
		possuiReserva=false;
		idade = this.aniversario.idade();
	}
	
	public  boolean equals(Usuario usuario){
		//Esse método serve para a comparação entre usuários usando seu atributo único (cpf)
		return (this.cpf==usuario.cpf)? true:false;
	}
	
	public String info() {
		//Esse método retorna uma string contendo as informações de um usuário para eventual impressão
		String txt;
		txt="Nome: "+nome+";\nCpf: "+cpf+";\nAniversário: "+aniversario.toString()+";\nEndereço: "+endereco.toString()+";\nJá agendado? "+((possuiReserva)?"Sim":"Não")+".";
		
		return txt;
	}
	
	public String cpf() {
		//Esse método retorna o cpf do usuário
		return cpf;
	}
	
	public void setReserva(boolean Reserva) {
		//Esse método altera o valor da reserva de um usuário
		this.possuiReserva=Reserva;
	}
	
	public boolean possuiReserva() {
		//Esse método retorna uma resposta indicando se o usuário tem ou não reserva
		return possuiReserva;
	}
	
	public boolean temIdade(int idade) {
		//Esse método avalia se um usuário tem possui idade maior ou igual a uma idade de referência
		return this.idade>=idade;
	}
	
	public Point getEndereco() {
		//Esse método retorna o ponto de endereço do usuário
		return endereco;
	}
	
}
