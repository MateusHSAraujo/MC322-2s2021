package com.unicamp.mc322.lab04.Operadores;
import java.time.ZonedDateTime;
public class Date {
	//Essa classe serve basicamente como estrutura de dados para a operação das demais classes
	private int dia;
	private int mes;
	private int ano;
	
	public Date(int d,int m,int y) {
		dia=d;
		mes=m;
		ano=y;
	}
	
	public Date() {
		ZonedDateTime agora = ZonedDateTime.now();
		int diaAtual = agora.getDayOfMonth(), mesAtual = agora.getMonthValue(),anoAtual=agora.getYear();
		dia = diaAtual;
		mes=mesAtual;
		ano=anoAtual;
	}
	
	public int idade() { // Esse método diz quantos anos houveram desde uma certa data
		ZonedDateTime agora = ZonedDateTime.now();
		int idade,diaAtual = agora.getDayOfMonth(), mesAtual = agora.getMonthValue(),anoAtual=agora.getYear();
		idade=anoAtual-ano;
		if (mesAtual<mes) {
			idade--;
		} else if(mesAtual==mes && diaAtual<dia) {
			idade--;
		}
		return idade;
	}
	
	public String toString() {
		return String.format("%d/%02d/%04d",dia,mes,ano);
	}
}
