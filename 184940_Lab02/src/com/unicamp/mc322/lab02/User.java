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
		this.aniversario = "~~Não fornecido~~";
		this.genero = "~~Não fornecido~~";
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
				System.out.println("Operação inválida. Sua pasta de playlist está lotada.");
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
			System.out.println("Transferência inválida. A playlist do usuário "+ this.nome +" está cheia.");
			return;
		}
		
		if (this.listaDePlaylists.lastIndexOf(playList)==-1) {
			System.out.println("Transferência inválida. O usuário "+ this.nome +" não tem essa playlist.");
			return;
		}
		
		switch(hasSpace(user)) {
		case 1:
			this.removePlaylist(playList,true);
			user.addPlaylist(playList,true);
			System.out.println("Transferência de playlist finalizada com sucesso.");
			break;
		case 2:
			System.out.println("Transferência inválida. A playlist do usuário "+ user.getName()+" está cheia.");
		}
	}
	
	
	public void getPremium() {
		if (this.ehPremium) {
			System.out.println("Operação inválida. Esse usuário já é assinante.");
			return;
		} else {
			this.ehPremium = true;
			System.out.println("O usuário"+this.nome+ "agora é assinante");
		}
	}
	
	
	public void cancelPremium() {
		if (this.ehPremium==false) {
			System.out.println("Operação inválida. Esse usuário ainda não é assinante");
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
		System.out.println("Informações do Usuário:");
		System.out.println("Nome: "+this.nome);
		System.out.println("Cpf: "+this.cpf);
		System.out.println("Aniverspario: "+this.aniversario);
		System.out.println("Gênero: "+this.genero);
		if (this.ehPremium) {
			System.out.println("Assinante?: Sim ");
		} else {
			System.out.println("Assinante?: Não ");
		}
		return "--------------------------------------------";//Só estou retornando um string por que é pedido no main fornecido
	}
	
	public String showPlaylists() {
		System.out.println("--------------------------------------------");
		System.out.println("Usuário: "+this.nome);
		System.out.println("Número de Playlist: "+this.listaDePlaylists.size());
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
			System.out.printf("\tGênero: %s\n",generoPl);
			System.out.printf("\tDuração: %d segundos\n",duracaoPl);
			System.out.printf("\tNúmero de músicas: %d\n",sonsPl.size());
			System.out.println("\tMúsicas:");
			ListIterator<Song> iter = sonsPl.listIterator(); //iter de iterator
			while(iter.hasNext()) {
				Song somAtual = iter.next();
				System.out.printf("\t- %s - %s;\n",somAtual.getName(),somAtual.getArtist());
			}	
		}
		return "--------------------------------------------";//Só estou retornando um string por que é pedido no main fornecido
	}
	
	
	
	
	
	
	
}

