/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.negocio;

import br.com.nsinova.teste.dao.IProfessor;
import java.sql.Connection;

/**
 *
 * @author MIKE
 */
public class Professor {

    private final Connection conexao;
    private IProfessor professorDao;

    public Professor(Connection conexao) throws Exception {
        if (conexao == null) {
            throw new Exception("Erro ao iniciar Professor, conexão não pode ser nula");
        }
        this.conexao = conexao;
        inicializarProfessorDao();
    }

    private void inicializarProfessorDao() throws Exception {
        this.professorDao = new br.com.nsinova.teste.dao.postgres.Professor(conexao);
    }

    public int manter(br.com.nsinova.teste.modelo.Professor professor) throws Exception {
        return professorDao.manter(professor);
    }

    public int alterar(br.com.nsinova.teste.modelo.Professor professor) throws Exception {
        return professorDao.alterar(professor);
    }
}
