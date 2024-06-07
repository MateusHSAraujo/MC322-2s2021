package com.unicamp.mc322.lab13.IOrder;

import java.time.LocalDate;

import com.unicamp.mc322.lab13.Person.PersonPT;

public class FisicalOrder extends Order{
	//Essa classe representa um pedido feito na loja física e, por isso, herda da classe ordem. Essa separação  dos pedidos de diferentes tipos em duas classes diferentes foi feita com o intuito facilitar futuras possíveis extensões do código, imaginando-se que, em situações mais realistas, pedidos feitos na loja física apresentassem métodos e/ou atributos diferentes dos pedidos feitos pela internet. 
	
	public FisicalOrder(PersonPT person){
		super(person,LocalDate.now().toString()+"_"+person.getCpf()+"_FisicalOrder");
	}
	
}
