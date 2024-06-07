package com.unicamp.mc322.lab02;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class User {
	private String nome;
	private String cpf;
	private String aniversario;
	private String genero;
	public boolean ehPremium;
	private List<Playlist> listaDePlaylists;
	
	public User(String nome, String cpf) {
		this.nome=nome;
		this.cpf=cpf;
		this.aniversario = "~~N�o fornecido~~";
		this.genero = "~~N�o fornecido~~";
		this.ehPremium = false;
		this.listaDePlaylists = new ArrayList<Playlist>();
	}
	
	public User(String nome, String cpf, String aniversario, String genero) {
		this.nome=nome;
		this.cpf=cpf;
		this.aniversario=aniversario;
		this.genero=genero;
		this.listaDePlaylists = new ArrayList<Playlist>();
		
	}
	
	public String getName() {
		return this.nome;
	}
	
	private int hasSpace(User user) {
		if (user.ehPremium==true) {
			if(listaDePlaylists.size()<10) {
				return 1;
			} else {
				return 2;
			}
		} else {
			if (listaDePlaylists.size()<3 ) {
				return 1;
			} else {
				return 2;
			}
		}
	}
	
	public void addPlaylist(Playlist playList) {
		if (hasSpace(this)==1) {
				listaDePlaylists.add(playList);
				System.out.println("Playlist adiciconada com sucesso.");
		} else {
				System.out.println("Opera��o inv�lida. Sua pasta de playlist est� lotada.");
		}
	
	}
	
	public void removePlaylist(Playlist playList) {
		if(listaDePlaylists.size()>0) {
			listaDePlaylists.remove(playList);
			System.out.println("Playlist removida com sucesso.");
		}
	}
	
	public void addPlaylist(Playlist playList, boolean exchange) {
		if(exchange) {
			if (hasSpace(this)==1) {
				listaDePlaylists.add(playList);
			} else {
			}
		}
		
	}
	
	public void removePlaylist(Playlist playList, boolean exchange) {
		if (exchange) {
			if(listaDePlaylists.size()>0) {
				listaDePlaylists.remove(playList);
			}
		}
		
	}	
	
	public void transferPlaylist(User user, Playlist playList) {
		if(this.listaDePlaylists.size()==0) {
			System.out.println("Transfer�ncia inv�lida. A playlist do usu�rio "+ this.nome +" est� cheia.");
			return;
		}
		
		if (this.listaDePlaylists.lastIndexOf(playList)==-1) {
			System.out.println("Transfer�ncia inv�lida. O usu�rio "+ this.nome +" n�o tem essa playlist.");
			return;
		}
		
		switch(hasSpace(user)) {
		case 1:
			this.removePlaylist(playList,true);
			user.addPlaylist(playList,true);
			System.out.println("Transfer�ncia de playlist finalizada com sucesso.");
			break;
		case 2:
			System.out.println("Transfer�ncia inv�lida. A playlist do usu�rio "+ user.getName()+" est� cheia.");
		}
	}
	
	
	public void getPremium() {
		if (this.ehPremium) {
			System.out.println("Opera��o inv�lida. Esse usu�rio j� � assinante.");
			return;
		} else {
			this.ehPremium = true;
			System.out.println("O usu�rio"+this.nome+ "agora � assinante");
		}
	}
	
	
	public void cancelPremium() {
		if (this.ehPremium==false) {
			System.out.println("Opera��o inv�lida. Esse usu�rio ainda n�o � assinante");
		}
		else {
			this.ehPremium=false;
			while(listaDePlaylists.size()>3) {
					listaDePlaylists.remove(3);
				}
			for(Playlist playList : listaDePlaylists) {
				playList.downgrade();
			}
			
		}
	}
	
	
	public String showInformation() {
		System.out.println("--------------------------------------------");
		System.out.println("Informa��es do Usu�rio:");
		System.out.println("Nome: "+this.nome);
		System.out.println("Cpf: "+this.cpf);
		System.out.println("Aniverspario: "+this.aniversario);
		System.out.println("G�nero: "+this.genero);
		if (this.ehPremium) {
			System.out.println("Assinante?: Sim ");
		} else {
			System.out.println("Assinante?: N�o ");
		}
		return "--------------------------------------------";//S� estou retornando um string por que � pedido no main fornecido
	}
	
	public String showPlaylists() {
		System.out.println("--------------------------------------------");
		System.out.println("Usu�rio: "+this.nome);
		System.out.println("N�mero de Playlist: "+this.listaDePlaylists.size());
		ListIterator<Playlist> itr = listaDePlaylists.listIterator(); // itr de iterator
		int i=0;
		while(itr.hasNext()) {
			i++;
			Playlist playListAtual = itr.next();
			String nomePl = playListAtual.getName();
			String generoPl = playListAtual.getGenre();
			int duracaoPl = playListAtual.getDuration();
			List<Song> sonsPl = playListAtual.getSongs();
			System.out.printf("Playlist %d: %s\n",i,nomePl);
			System.out.printf("\tG�nero: %s\n",generoPl);
			System.out.printf("\tDura��o: %d segundos\n",duracaoPl);
			System.out.printf("\tN�mero de m�sicas: %d\n",sonsPl.size());
			System.out.println("\tM�sicas:");
			ListIterator<Song> iter = sonsPl.listIterator(); //iter de iterator
			while(iter.hasNext()) {
				Song somAtual = iter.next();
				System.out.printf("\t- %s - %s;\n",somAtual.getName(),somAtual.getArtist());
			}	
		}
		return "--------------------------------------------";//S� estou retornando um string por que � pedido no main fornecido
	}
	
	
	
	
	
	
	
}

