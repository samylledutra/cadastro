package br.edu.iftm.tspi.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PessoaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pessoa> listarPessoas() {
        String sql = "select id, nome, email, endereco from pessoa";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pessoa.class));
    }

    public Pessoa buscarPorId(Long id) {
        String sql = "select id, nome, email, endereco from pessoa where id = ?";
        List<Pessoa> pessoas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pessoa.class), id);
        if (pessoas != null && !pessoas.isEmpty()) {
            return pessoas.get(0);
        } else {
            return null;
        }
    }

    public List<Pessoa> buscarPorNome(String nome) {
        String sql = "select id, nome, email, endereco from pessoa where lower(nome) like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pessoa.class), "%" + nome.toLowerCase() + "%");
    }

    @Transactional
    public void salvar(Pessoa pessoa) {
        String sql = "insert into pessoa (nome, email, endereco) values (?, ?, ?)";
        jdbcTemplate.update(sql, pessoa.getNome(), pessoa.getEmail(), pessoa.getEndereco());
    }

    @Transactional
    public void atualizar(Pessoa pessoa) {
        String sql = "update pessoa set nome = ?, email = ?, endereco = ? where id = ?";
        jdbcTemplate.update(sql, pessoa.getNome(), pessoa.getEmail(), pessoa.getEndereco(), pessoa.getId());
    }

    @Transactional
    public void excluir(Long id) {
        String sql = "delete from pessoa where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
