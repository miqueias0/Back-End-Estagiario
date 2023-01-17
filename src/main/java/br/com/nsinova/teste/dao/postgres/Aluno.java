/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.dao.postgres;

import br.com.nsinova.teste.dao.IAluno;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author miqueias.nadaluti
 */
public class Aluno implements IAluno {

    private final Connection conexao;

    public Aluno(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int manter(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("INSERT INTO aluno (pessoa_cpf, matricula) VALUES(?,?)");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, aluno.getCpf());
        comando.setString(2, aluno.getNumeroMatricula());

        return comando.executeUpdate();
    }

    @Override
    public int alterar(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("UPDATE aluno SET matricula = ? WHERE pessoa_cpf = ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, aluno.getNumeroMatricula().isEmpty() ? null : aluno.getNumeroMatricula());
        comando.setString(2, aluno.getCpf());

        return comando.executeUpdate();
    }

}
