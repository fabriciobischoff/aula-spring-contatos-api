package com.algaworkscontatos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.algaworkscontatos.model.Contato;

@Service
public class ContatosService {
	
	private static final ArrayList<Contato> LIST_CONTATOS = new ArrayList<>();
	
	static {
		LIST_CONTATOS.add(new Contato("1", "Maria", "+55 34 00000 0000"));
		LIST_CONTATOS.add(new Contato("2", "João", "+55 34 00000 0000"));
		LIST_CONTATOS.add(new Contato("3", "Normandes", "+55 34 00000 0000"));
		LIST_CONTATOS.add(new Contato("4","Thiago", "+55 34 00000 0000"));
		LIST_CONTATOS.add(new Contato("5", "Alexandre", "+55 34 00000 0000"));
	}
	
	public List<Contato> listar() {
		return LIST_CONTATOS;
	}
	
	public void cadastrar(Contato contato) {
		String id = UUID.randomUUID().toString(); // gera o id
		contato.setId(id);
		
		LIST_CONTATOS.add(contato);
	}
	
	public void atualizar(String id, Contato contato) {
		Integer indice = procurarIndiceContato(id);
		
		Contato contatoVelho = LIST_CONTATOS.get(indice);
		
		LIST_CONTATOS.remove(contatoVelho);
		LIST_CONTATOS.add(indice, contato);
	}
	
	public void excluir(String id) {
		Contato contato = buscar(id);
		
		LIST_CONTATOS.remove(contato);
	}
	
	public Contato buscar(String id) {
		Integer indice = procurarIndiceContato(id);
		
		if (indice != null) {
			Contato contato = LIST_CONTATOS.get(indice);
			return contato;
		}
		
		return null;
	}
	
	public Integer procurarIndiceContato(String id) {
		for (int i=0; i < LIST_CONTATOS.size(); i++) {
			Contato contato = LIST_CONTATOS.get(i);
			
			if (contato.getId().equals(id)) {
				return i;
			}
		}
		
		return null;
	}

}
