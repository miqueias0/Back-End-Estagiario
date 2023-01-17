/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.nsinova.teste.dao;

import br.com.nsinova.teste.modelo.Pessoa;

/**
 *
 * @author miqueias.nadaluti
 */
public interface IEndereco {
    
    public br.com.nsinova.teste.modelo.Endereco obterEndereco(String cpf) throws Exception;
    public int manter(Pessoa pessoa) throws Exception;
    public int alterar(Pessoa pessoa) throws Exception;
}
