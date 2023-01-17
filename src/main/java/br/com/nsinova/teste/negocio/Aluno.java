/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.negocio;

import br.com.nsinova.teste.dao.IAluno;
import br.com.nsinova.teste.dao.IPessoa;
import java.sql.Connection;

/**
 *
 * @author MIKE
 */
public class Aluno {

    private final Connection conexao;
    private IAluno alunoDao;
    private IPessoa pessoaDao;

    public Aluno(Connection conexao) throws Exception {
        if (conexao == null) {
            throw new Exception("Erro ao iniciar Aluno, conexão não pode ser nula");
        }
        this.conexao = conexao;
        inicializarAlunoDao();
    }

    private void inicializarAlunoDao() throws Exception {
        this.alunoDao = new br.com.nsinova.teste.dao.postgres.Aluno(conexao);
        this.pessoaDao = new br.com.nsinova.teste.dao.postgres.Pessoa(conexao);
    }

    public int manter(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        return alunoDao.manter(aluno);
    }

    public int alterar(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        return alunoDao.alterar(aluno);
    }

}
