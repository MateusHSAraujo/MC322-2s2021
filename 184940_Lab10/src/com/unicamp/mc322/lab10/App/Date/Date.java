package com.unicamp.mc322.lab10.App.Date;

public class Date {
	//Estrutura de dados para facilitar o cadastro da data de nascimento dos usuários
	private int dia;
	private int mes;
	private int ano;
	
	public Date(int d, int m, int a){
		dia=d;
		mes=m;
		ano=a;
	}
	
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d",dia,mes,ano);
	}
}
