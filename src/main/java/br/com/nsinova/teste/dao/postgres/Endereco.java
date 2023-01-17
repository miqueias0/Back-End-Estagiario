/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.dao.postgres;

import br.com.nsinova.teste.dao.IEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author miqueias.nadaluti
 */
public class Endereco implements IEndereco {

    private final Connection conexao;

    public Endereco(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int manter(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("INSERT INTO endereco (pessoa_cpf, logradouro, numero, municipio, uf) VALUES (?,?,?,?,?)");
        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getCpf());
        comando.setString(2, pessoa.getEndereco().getLogradouro());
        comando.setString(3, pessoa.getEndereco().getNumero());
        comando.setString(4, pessoa.getEndereco().getMunicipio());
        comando.setString(5, pessoa.getEndereco().getUf());

        return comando.executeUpdate();
    }

    @Override
    public int alterar(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("UPDATE endereco SET logradouro = ?, numero = ?, municipio = ?, uf = ? WHERE pessoa_cpf = ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getEndereco().getLogradouro().isEmpty() ? null : pessoa.getEndereco().getLogradouro());
        comando.setString(2, pessoa.getEndereco().getNumero().isEmpty() ? null : pessoa.getEndereco().getNumero());
        comando.setString(3, pessoa.getEndereco().getMunicipio().isEmpty() ? null : pessoa.getEndereco().getMunicipio());
        comando.setString(4, pessoa.getEndereco().getUf().isEmpty() ? null : pessoa.getEndereco().getUf());
        comando.setString(5, pessoa.getCpf());

        return comando.executeUpdate();
    }

    @Override
    public br.com.nsinova.teste.modelo.Endereco obterEndereco(String cpf) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("SELECT * FROM endereco e WHERE e.pessoa_cpf ILIKE ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());
        comando.setString(1, "%".concat(cpf).concat("%"));
        ResultSet resultSet = comando.executeQuery();

        return resultSet.next() ? montarEndereco(resultSet) : null;
    }

    private br.com.nsinova.teste.modelo.Endereco montarEndereco(ResultSet resultSet) throws Exception {

        br.com.nsinova.teste.modelo.Endereco endereco = new br.com.nsinova.teste.modelo.Endereco();

        endereco.setLogradouro(resultSet.getString("logradouro"));
        endereco.setMunicipio(resultSet.getString("municipio"));
        endereco.setNumero(resultSet.getString("numero"));
        endereco.setUf(resultSet.getString("uf"));

        return endereco;
    }

}
