/**
 * 
 */
package com.edersongs.jsb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@Entity
@Table(name = "TUSUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -9117737550696009240L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	private String nome;
	
	private String login;
	
	@JsonIgnore
	private String senha;
	
	@ManyToMany
	@JoinTable(name = "TUSUARIOGRUPO", joinColumns = @JoinColumn(name = "codusr"), inverseJoinColumns = @JoinColumn(name = "codgrp"))	
	private List<Grupo> grupos;
	
	public Usuario() {}
	
	public Usuario(String nome, String login, String senha, List<Grupo> grupos) {
		
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupos = grupos;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
