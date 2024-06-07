package com.unicamp.mc322.lab04.VacinaCovid;
import com.unicamp.mc322.lab04.Operadores.*;
public class Runner {
	public static void main(String[] args) {

        VacinaCovid app = new VacinaCovid();
        app.setIdadeMinimaAtendida(60);

        app.cadastrarUsuario("Jose da Silva", "123.456.789-01",new Date(1960,12,03), new Point(10,30));
        app.cadastrarUsuario("Maria Assuncao", "321.654.987-10", new Date(1999,4,11), new Point(-43,101));
        
        
        app.cadastrarPosto("SOUSAS", new Point(0,20), 5,new String[] {"sexta","sabado","domingo"});
        app.cadastrarPosto("BARAO GERALDO", new Point(-20,40), 2,new String[] {"sexta","sabado","domingo"});
        app.imprimirSituacaoPostos();
        
        Reserva r1 = app.agendar("123.456.789-01", "SOUSAS");
        Reserva r2 = app.agendarPerto("321.654.987-10"); //Como o Runner exige uma reserva retornada, configurei para que o coprovante de reserva para casos não efetivados sejam nulos.
        
        app.imprimirSituacaoPostos();
        app.imprimirCadastroUsuarios();
        
        

	}
}
