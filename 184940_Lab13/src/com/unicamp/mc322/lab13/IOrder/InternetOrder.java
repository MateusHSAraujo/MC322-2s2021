package com.unicamp.mc322.lab13.IOrder;

import java.time.LocalDate;

import com.unicamp.mc322.lab13.Person.PersonPT;

public class InternetOrder extends Order{
	//Essa classe representa um pedido feito pela internet e, por isso, herda da classe ordem. Essa separa��o em dos pedidos de diferentes tipos em duas classes diferentes foi feita com o intuito facilitar futuras poss�veis extens�es do c�digo, imaginando-se que, em situa��es mais realistas, pedidos feitos na loja f�sica apresentassem m�todos e/ou atributos diferentes dos pedidos feitos pela internet.
	
	public InternetOrder(PersonPT person){
		super(person,LocalDate.now().toString()+"_"+person.getCpf()+"_IntenetOrder");
	}
	
	
}
