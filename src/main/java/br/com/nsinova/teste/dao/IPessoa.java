/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.nsinova.teste.dao;

import br.com.nsinova.teste.modelo.Pessoa;
import java.util.List;

/**
 *
 * @author miqueias.nadaluti
 */
public interface IPessoa {
    
    public List<Pessoa> obterLista(String textoPesquisa) throws Exception;
    public Pessoa obterPorCpf(String cpf) throws Exception;
    public int manter(Pessoa pessoa, String tipo) throws Exception;
    public int alterar(Pessoa pessoa) throws Exception;
    public int excluir(Pessoa pessoa) throws Exception;
}
