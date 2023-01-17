/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.dao.postgres;

import br.com.nsinova.teste.dao.IProfessor;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author miqueias.nadaluti
 */
public class Professor implements IProfessor {

    private final Connection conexao;

    public Professor(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int manter(br.com.nsinova.teste.modelo.Professor professor) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("INSERT INTO professor (pessoa_cpf, especialidade, conhecimentos) VALUES(?,?,?)");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, professor.getCpf());
        comando.setString(2, professor.getEspecialidade());

        if (professor.getConhecimentos() != null) {
            String conhecimento = "";
            for (String item : professor.getConhecimentos()) {
                conhecimento += conhecimento.isEmpty() ? item : "," + item;
            }
            comando.setString(3, conhecimento);
        } else {
            comando.setNull(3, 0);
        }

        return comando.executeUpdate();
    }

    @Override
    public int alterar(br.com.nsinova.teste.modelo.Professor professor) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("UPDATE professor SET especialidade = ?, conhecimentos = ? WHERE pessoa_cpf = ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, professor.getEspecialidade().isEmpty() ? null : professor.getEspecialidade());

        if (professor.getConhecimentos() != null) {
            String conhecimento = "";
            for (String item : professor.getConhecimentos()) {
                conhecimento += conhecimento.isEmpty() ? item : "," + item;
            }
            comando.setString(2, conhecimento);
        } else {
            comando.setNull(2, 0);
        }
        comando.setString(3, professor.getCpf());

        return comando.executeUpdate();
    }
}
