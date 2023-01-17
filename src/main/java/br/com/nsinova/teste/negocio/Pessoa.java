/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.negocio;

import br.com.nsinova.teste.dao.IPessoa;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MIKE
 */
public class Pessoa {

    private final Connection conexao;
    private IPessoa pessoaDao;

    public Pessoa(Connection conexao) throws Exception {
        if (conexao == null) {
            throw new Exception("Erro ao iniciar Pessoa, conexão não pode ser nula");
        }
        this.conexao = conexao;
        inicializarPessoaDao();
    }

    private void inicializarPessoaDao() throws Exception {
        this.pessoaDao = new br.com.nsinova.teste.dao.postgres.Pessoa(conexao);
    }

    public List<br.com.nsinova.teste.modelo.Pessoa> obterLista(String textoPesquisa) throws Exception {
        return pessoaDao.obterLista(textoPesquisa);
    }

    public br.com.nsinova.teste.modelo.Pessoa obterPorCpf(String cpf) throws Exception {
        return pessoaDao.obterPorCpf(cpf);
    }

    public int manter(br.com.nsinova.teste.modelo.Pessoa pessoa, String tipo) throws Exception {
        if (pessoaDao.manter(pessoa, tipo) == 0) {
            throw new Exception("Erro ao manter Pessoa");
        }
        if (pessoa.getEndereco() != null && !pessoa.getEndereco().equals(new br.com.nsinova.teste.modelo.Endereco())) {
            br.com.nsinova.teste.dao.IEndereco endereco = new br.com.nsinova.teste.dao.postgres.Endereco(conexao);
            if (endereco.manter(pessoa) == 0) {
                throw new Exception("Erro ao manter Endereco");
            }
        }
        return 1;
    }

    public int alterar(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        if (pessoaDao.alterar(pessoa) == 0) {
            throw new Exception("Erro ao alterar Pessoa");
        }
        if (pessoa.getEndereco() != null && !pessoa.getEndereco().equals(new br.com.nsinova.teste.modelo.Endereco())) {
            br.com.nsinova.teste.dao.IEndereco endereco = new br.com.nsinova.teste.dao.postgres.Endereco(conexao);
            if (endereco.obterEndereco(pessoa.getCpf()) != null) {
                if (endereco.alterar(pessoa) == 0) {
                    throw new Exception("Erro ao alterar Endereco");
                }
            } else {
                if (endereco.manter(pessoa) == 0) {
                    throw new Exception("Erro ao manter Endereco");
                }
            }
        }
        return 1;
    }

    public int excluir(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        return pessoaDao.excluir(pessoa);
    }
}
