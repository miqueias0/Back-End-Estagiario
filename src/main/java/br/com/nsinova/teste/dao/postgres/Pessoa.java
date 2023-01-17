/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.teste.dao.postgres;

import br.com.nsinova.teste.dao.IPessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author miqueias.nadaluti
 */
public class Pessoa implements IPessoa {

    private final Connection conexao;

    public Pessoa(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public List<br.com.nsinova.teste.modelo.Pessoa> obterLista(String textoPesquisa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("SELECT * FROM pessoa p LEFT OUTER JOIN endereco e ON e.pessoa_cpf = p.cpf LEFT OUTER JOIN aluno a ON a.pessoa_cpf = p.cpf LEFT OUTER JOIN professor pf ON pf.pessoa_cpf = p.cpf WHERE p.cpf ILIKE ? OR p.nome ILIKE ? OR p.tipo ILIKE ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());
        for (int i = 1; i <= 3; i++) {
            comando.setString(i, "%".concat(textoPesquisa != null ? textoPesquisa.toLowerCase() : "").concat("%"));
        }
        return montarListaPessoa(comando.executeQuery());
    }

    @Override
    public br.com.nsinova.teste.modelo.Pessoa obterPorCpf(String cpf) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("SELECT * FROM pessoa p LEFT OUTER JOIN endereco e ON e.pessoa_cpf = p.cpf LEFT OUTER JOIN aluno a ON a.pessoa_cpf = p.cpf LEFT OUTER JOIN professor pf ON pf.pessoa_cpf = p.cpf WHERE p.cpf ILIKE ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());
        comando.setString(1, cpf);
        ResultSet resultSet = comando.executeQuery();

        return resultSet.next() ? montarPessoa(resultSet) : null;
    }

    @Override
    public int manter(br.com.nsinova.teste.modelo.Pessoa pessoa, String tipo) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("INSERT INTO pessoa (cpf, nome, data_nascimento, tipo) VALUES(?,?,?,?)");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getCpf());
        comando.setString(2, pessoa.getNome());
        comando.setDate(3, pessoa.getDataNascimento() != null ? new java.sql.Date(pessoa.getDataNascimento().getTime()) : null);
        comando.setString(4, tipo);

        return comando.executeUpdate();
    }

    @Override
    public int alterar(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("UPDATE pessoa SET nome = ?, data_nascimento = ? WHERE cpf = ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getNome());
        comando.setDate(2, pessoa.getDataNascimento() != null ? new java.sql.Date(pessoa.getDataNascimento().getTime()) : null);
        comando.setString(3, pessoa.getCpf());

        return comando.executeUpdate();
    }

    @Override
    public int excluir(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("DELETE FROM pessoa WHERE cpf = ?");

        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getCpf());

        return comando.executeUpdate();
    }

    private br.com.nsinova.teste.modelo.Pessoa montarPessoa(ResultSet resultSet) throws Exception {
        br.com.nsinova.teste.modelo.Pessoa pessoa;
        String tipo = resultSet.getString("tipo");

        if (tipo != null && tipo.equalsIgnoreCase("aluno")) {
            pessoa = new br.com.nsinova.teste.modelo.Aluno();
            pessoa.setCpf(resultSet.getString("cpf"));
            pessoa.setDataNascimento(new Date(resultSet.getDate("data_nascimento").getTime()));
            pessoa.setNome(resultSet.getString("nome"));
            ((br.com.nsinova.teste.modelo.Aluno) pessoa).setNumeroMatricula(resultSet.getString("matricula") != null ? resultSet.getString("matricula") : new String());
        } else if (tipo != null && tipo.equalsIgnoreCase("professor")) {
            pessoa = new br.com.nsinova.teste.modelo.Professor();
            pessoa.setCpf(resultSet.getString("cpf"));
            pessoa.setDataNascimento(new Date(resultSet.getDate("data_nascimento").getTime()));
            pessoa.setNome(resultSet.getString("nome"));
            ((br.com.nsinova.teste.modelo.Professor) pessoa).setEspecialidade(resultSet.getString("especialidade") != null ? resultSet.getString("especialidade") : new String());
            String conhecimento = resultSet.getString("conhecimentos") != null ? resultSet.getString("conhecimentos") : new String();
            if (conhecimento != null) {
                List<String> conhecimentos = new ArrayList<>();
                for (String item : conhecimento.split(",")) {
                    conhecimentos.add(item.trim());
                }
                ((br.com.nsinova.teste.modelo.Professor) pessoa).setConhecimentos(conhecimentos);
            }
        } else {
            throw new Exception("Tipo de pessoa não especificada");
        }
        br.com.nsinova.teste.modelo.Endereco endereco = new br.com.nsinova.teste.modelo.Endereco();
        endereco.setLogradouro(resultSet.getString("logradouro"));
        endereco.setMunicipio(resultSet.getString("municipio"));
        endereco.setNumero(resultSet.getString("numero"));
        endereco.setUf(resultSet.getString("uf"));
        pessoa.setEndereco(endereco);

        return pessoa;
    }

    private List<br.com.nsinova.teste.modelo.Pessoa> montarListaPessoa(ResultSet resultSet) throws Exception {
        List<br.com.nsinova.teste.modelo.Pessoa> pessoas = new ArrayList<>();
        while (resultSet.next()) {
            pessoas.add(montarPessoa(resultSet));
        }
        return pessoas;
    }

}
