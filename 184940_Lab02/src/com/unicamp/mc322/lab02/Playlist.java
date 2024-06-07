package com.unicamp.mc322.lab02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;

public class Playlist {
	
	private String nome;
	private String genero;
	private List<Song> listaDeSons;
	private User usuario;
	private int duracaoTotal;
	private List<Song> filaDeReproducao;
	private boolean playing ;
	
	public Playlist(String nome, String genero) {
		this.nome=nome;
		this.genero=genero;
		this.listaDeSons = new ArrayList<Song>();
		this.filaDeReproducao = new ArrayList<Song>();
	}
	
	public String getName() {
		return this.nome;
	}
	
	public String getGenre() {
		return this.genero;
	}
	
	public int getDuration() {
		return this.duracaoTotal;
	}
	
	public List<Song> getSongs() {
		return this.listaDeSons;
	}
	
	
	public void addSong(Song musica) {
		if(usuario==null || usuario.ehPremium==false) {
			if(listaDeSons.size()<10) {
				listaDeSons.add(musica);
				listaDeSons.sort(Comparator.comparing(Song::getName));
			} else {
				System.out.println("Essa playlist est� cheia. Favor atualizar para premium ou remover outras m�sicas");
			}
		} else {
			if(listaDeSons.size()<100) {
				listaDeSons.add(musica);
			}
			else {
				System.out.println("Essa playlist est� cheia. Favor remover outras m�sicas");
			}
		}
		
	}
	
	public void removeSong(Song musica) {
		if (listaDeSons.size()>0) {
			listaDeSons.remove(musica);
		}
	}
	
	public void shortestSong() {
		System.out.println("A m�sica de menor dura��o �:");
		Optional<Song> menorMusica= listaDeSons.stream().min(Comparator.comparing(Song::getDuration));
		System.out.printf("\t '%s' -- '%s' -- (%d segundos)\n", menorMusica.get().getName(),menorMusica.get().getArtist(),menorMusica.get().getDuration());
	}
	
	public void longestSong() {
		System.out.println("A m�sica de maior dura��o �:");
		Optional<Song> maiorMusica= listaDeSons.stream().max(Comparator.comparing(Song::getDuration));
		System.out.printf("\t '%s' -- '%s' -- (%d segundos)\n", maiorMusica.get().getName(),maiorMusica.get().getArtist(),maiorMusica.get().getDuration());
	}
	
	public void playlistDuration() {
		System.out.printf("A dura��o total da playlist %s � de %d segundos.\n",this.nome,this.duracaoTotal);
	}
	
	public void playlistMDuration() {
		System.out.printf("A dura��o m�dia das m�sicas da playlist %s � de %d segundos.\n",this.nome,this.duracaoTotal/listaDeSons.size());
	}
	
	private class registroDeArtista{
			public String nome;
			public int ocorrencia;
			public List<String> musicasNaPlaylist;
			public registroDeArtista() {
				this.musicasNaPlaylist = new ArrayList<String>();
			}

			public int getFreq() {
				return this.ocorrencia;
			}
		}
	
	private int getindex(String nome, List<registroDeArtista> reg) {
		ListIterator<registroDeArtista> itr = reg.listIterator(); //itr de iterator
		int indice =0;
		while(itr.hasNext()) {
			if (nome == itr.next().nome) {
				return indice;
			} else {
				indice++;
			}
		}
		return -1;
	}
	
	private registroDeArtista searchTopArtist(){
		
		List<registroDeArtista> registro = new ArrayList<registroDeArtista>();
		ListIterator<Song> ponteiro = listaDeSons.listIterator();
		
		while(ponteiro.hasNext()) {
			Song musicaAtual = ponteiro.next();
			String nomeMusica = musicaAtual.getName();
			String nomeArtista = musicaAtual.getArtist();

			int indice = getindex(nomeArtista, registro);
			if (indice >= 0) {
				registro.get(indice).ocorrencia++;
				registro.get(indice).musicasNaPlaylist.add(nomeMusica);
			} else {
				registroDeArtista novoRegistro = new registroDeArtista();
				novoRegistro.nome=nomeArtista;
				novoRegistro.ocorrencia=1;
				novoRegistro.musicasNaPlaylist.add(nomeMusica);
				registro.add(novoRegistro);
			}
		}
		Optional<registroDeArtista> topArtista = registro.stream().max(Comparator.comparing(registroDeArtista::getFreq));
		
		return topArtista.get();
		
	}
	
	public void topArtist() {
		System.out.printf("O artista com mais m�sicas na playlist \"%s\" �:\n",this.nome);
		registroDeArtista topArtista = searchTopArtist();
		System.out.printf("\t%s: ",topArtista.nome);
		ListIterator<String> itr = topArtista.musicasNaPlaylist.listIterator(); //itr de iterator
		while(itr.hasNext()) {
			String nomeMusica = itr.next();
			System.out.printf("%s -- ", nomeMusica);
		}
		System.out.println();
	}
	
	public Song play(){
		if (this.playing==true) {
			if(filaDeReproducao.size()==0) {
				filaDeReproducao.addAll(listaDeSons);
				this.playing=true;
				Song musica = filaDeReproducao.get(0);
				System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());
				filaDeReproducao.remove(0);
				return musica;
			} else {
				Song musica = filaDeReproducao.get(0);
				System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());
				filaDeReproducao.remove(0);
				return musica;
			}
		} else {
			filaDeReproducao.addAll(listaDeSons);
			this.playing=true;
			Song musica = filaDeReproducao.get(0);
			System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());
			filaDeReproducao.remove(0);
			return musica;
		}
	}
	
	public Song play(boolean shuffle){
		if (shuffle == true){
			if(filaDeReproducao.size()==0) {
				filaDeReproducao.addAll(listaDeSons);
				this.playing=true;
				Random rand = new Random();
				int indiceAleatorio = rand.nextInt(filaDeReproducao.size()-1);
				Song musica = filaDeReproducao.get(indiceAleatorio);
				System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());				
				filaDeReproducao.remove(indiceAleatorio);
				return musica;
			} else {
				if(filaDeReproducao.size()-1 == 0) {
					Song musica = filaDeReproducao.get(0);
					System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());				
					filaDeReproducao.remove(0);
					return musica;
				}
				Random rand = new Random();
				int indiceAleatorio = rand.nextInt(filaDeReproducao.size()-1);
				filaDeReproducao.remove(indiceAleatorio);
				Song musica = filaDeReproducao.get(indiceAleatorio);
				System.out.printf("Tocando agora: %s - %s\n",musica.getName(),musica.getArtist());				
				filaDeReproducao.remove(indiceAleatorio);
				return musica;
			}
		} else {
			return play();
		}
	}
	
	public void downgrade() {
		while(listaDeSons.size()>10) {
				listaDeSons.remove(10);
		}
		
	}
	
		
	
}
