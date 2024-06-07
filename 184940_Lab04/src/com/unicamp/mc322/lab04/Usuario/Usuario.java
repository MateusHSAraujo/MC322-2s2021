package com.unicamp.mc322.lab04.Usuario;

import com.unicamp.mc322.lab04.Operadores.*;


public class Usuario {
	private String nome;
	private String cpf;
	private Date aniversario;
	private int idade;//A inten��o desse atributo � armazenar a idade de um usu�rio considerando a data atual para facilitar opera��es futuras
	private Point endereco;
	private boolean possuiReserva; //A inten��o desse atributo � armazenar a informa��o de que o usu�rio possui ou n�o um agendamento pendente para facilitar futuras opera��es
	
	public Usuario(String nome, String cpf, Date aniversario,Point endereco) {
		//Esse m�todo constr�i um usu�rio recebendo seu nome, cpf, data de anivers�rio e endere�o
		this.nome=nome;
		this.cpf=cpf;
		this.aniversario=aniversario;
		this.endereco=endereco;
		possuiReserva=false;
		idade = this.aniversario.idade();
	}
	
	public  boolean equals(Usuario usuario){
		//Esse m�todo serve para a compara��o entre usu�rios usando seu atributo �nico (cpf)
		return (this.cpf==usuario.cpf)? true:false;
	}
	
	public String info() {
		//Esse m�todo retorna uma string contendo as informa��es de um usu�rio para eventual impress�o
		String txt;
		txt="Nome: "+nome+";\nCpf: "+cpf+";\nAnivers�rio: "+aniversario.toString()+";\nEndere�o: "+endereco.toString()+";\nJ� agendado? "+((possuiReserva)?"Sim":"N�o")+".";
		
		return txt;
	}
	
	public String cpf() {
		//Esse m�todo retorna o cpf do usu�rio
		return cpf;
	}
	
	public void setReserva(boolean Reserva) {
		//Esse m�todo altera o valor da reserva de um usu�rio
		this.possuiReserva=Reserva;
	}
	
	public boolean possuiReserva() {
		//Esse m�todo retorna uma resposta indicando se o usu�rio tem ou n�o reserva
		return possuiReserva;
	}
	
	public boolean temIdade(int idade) {
		//Esse m�todo avalia se um usu�rio tem possui idade maior ou igual a uma idade de refer�ncia
		return this.idade>=idade;
	}
	
	public Point getEndereco() {
		//Esse m�todo retorna o ponto de endere�o do usu�rio
		return endereco;
	}
	
}
