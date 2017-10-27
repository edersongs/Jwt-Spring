/**
 * 
 */
package com.edersongs.jsb.seguranca.jwt;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
public class JWTCredenciais {

	private String nomeUsuario;
	private String senha;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
