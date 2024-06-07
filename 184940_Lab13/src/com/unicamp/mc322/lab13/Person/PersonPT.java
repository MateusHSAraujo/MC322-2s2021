package com.unicamp.mc322.lab13.Person;
import java.time.LocalDate;
import java.time.Period;

public class PersonPT {
	//Essa classe identifica uma pessoa usuário do sistema capaz de fazer pedidos físicos ou online
	//Conforme indicado pelo enunciado, os dados básicos de uma pessoa são seu nome, seu cpf e sua idade
	private String name;
	private String cpf;
	private int age;
	
	public PersonPT(LocalDate birth, String cpf, String name) {
		//Esse é o construtor padrão da classe PersonPT
		this.name=name;
		this.cpf=cpf;
		age=Period.between(birth, LocalDate.now()).getYears(); //Aqui usei a classse Period da biblioteca java assim como foi aconselhado no enunciado
	}
	
	 
	public String getName() {
		//Getter para o nome do usuário
		return name;
	}
	
	public String getCpf() {
		//Getter para o cpf do usuário
		return cpf;
	}
	
	public int getAge() {
		//Getter para a idade do usuário
		return age;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt="Name: "+name+"; Cpf: "+cpf+"; Age: "+age;
		return txt;
	}
	
}
