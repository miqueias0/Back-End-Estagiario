/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.negocio;

import br.com.nsinova.teste.dao.IEndereco;
import java.sql.Connection;

/**
 *
 * @author MIKE
 */
public class Endereco {

    private final Connection conexao;
    private IEndereco enderecoDao;

    public Endereco(Connection conexao) throws Exception {
        if (conexao == null) {
            throw new Exception("Erro ao iniciar Endereco, conexão não pode ser nula");
        }
        this.conexao = conexao;
        inicializarEnderecoDao();
    }

    private void inicializarEnderecoDao() throws Exception {
        this.enderecoDao = new br.com.nsinova.teste.dao.postgres.Endereco(conexao);
    }

    public int manter(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        return enderecoDao.manter(pessoa);
    }

    public int alterar(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        return enderecoDao.alterar(pessoa);
    }

    public br.com.nsinova.teste.modelo.Endereco obterEndereco(String cpf) throws Exception {
        return enderecoDao.obterEndereco(cpf);
    }
}
