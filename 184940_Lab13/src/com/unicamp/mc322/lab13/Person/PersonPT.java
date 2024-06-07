package com.unicamp.mc322.lab13.Person;
import java.time.LocalDate;
import java.time.Period;

public class PersonPT {
	//Essa classe identifica uma pessoa usu�rio do sistema capaz de fazer pedidos f�sicos ou online
	//Conforme indicado pelo enunciado, os dados b�sicos de uma pessoa s�o seu nome, seu cpf e sua idade
	private String name;
	private String cpf;
	private int age;
	
	public PersonPT(LocalDate birth, String cpf, String name) {
		//Esse � o construtor padr�o da classe PersonPT
		this.name=name;
		this.cpf=cpf;
		age=Period.between(birth, LocalDate.now()).getYears(); //Aqui usei a classse Period da biblioteca java assim como foi aconselhado no enunciado
	}
	
	 
	public String getName() {
		//Getter para o nome do usu�rio
		return name;
	}
	
	public String getCpf() {
		//Getter para o cpf do usu�rio
		return cpf;
	}
	
	public int getAge() {
		//Getter para a idade do usu�rio
		return age;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt="Name: "+name+"; Cpf: "+cpf+"; Age: "+age;
		return txt;
	}
	
}
