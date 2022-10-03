package com.algaworkscontatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworkscontatos.model.Contato;
import com.algaworkscontatos.service.ContatosService;

@Controller
public class ContatosController {
	
	@Autowired
	private ContatosService contatosService;
		
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos") // abre a view de contatos
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		modelAndView.addObject("contatos", contatosService.listar());
		
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo") // abre a view para cadastrar contatos
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("formulario");
		modelAndView.addObject("contato", new Contato());
		
		return modelAndView;
	}
	
	@PostMapping("/contatos") // cadastra um novo contato
	public String cadastrar(Contato contato) {
		contatosService.cadastrar(contato);
		
		return "redirect:/contatos"; // redireciona para a página de contatos
	}
	
	@GetMapping("/contatos/{id}/editar") // abre a view formulario com os dados carregados
	public ModelAndView editar(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		Contato contato = contatosService.buscar(id);
		modelAndView.addObject("contato", contato);
		
		return modelAndView;
	}
	
	@PutMapping("/contatos/{id}") // atualiza o contato
	public String atualizar(@PathVariable String id, Contato contato) {
		contatosService.atualizar(id, contato);
		
		return "redirect:/contatos"; // redireciona para a página de contatos
	}
	
	@GetMapping("/contatos/{id}/excluir")
	public String remover(@PathVariable String id) {
		contatosService.excluir(id);
		
		return "redirect:/contatos"; // redireciona para a página de contatos
	}

}
