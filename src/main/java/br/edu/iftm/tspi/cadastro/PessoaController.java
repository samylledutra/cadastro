package br.edu.iftm.tspi.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PessoaController {

    @Autowired
    private PessoaDao pessoaDao;

    @RequestMapping("pessoas")
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoaDao.listarPessoas());
        return "listar_pessoas";

    }

    @PostMapping("pessoas")
    public String inserirPessoa(Pessoa pessoa, Model model) {
        if (pessoa.getId() == null) {
            pessoaDao.salvar(pessoa);
        } else {
            pessoaDao.atualizar(pessoa);
        }
        return listarPessoas(model);
    }

    @RequestMapping("/novo")
    public String novoCadastro(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "formulario_pessoa";
    }

    @PostMapping("/pessoas/salvar")
    public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
        pessoaDao.salvar(pessoa);
        return "redirect:/pessoas";
    }

    @RequestMapping("/pessoasParametro")
    public String buscarPessoasPorNome(@RequestParam(value = "nome", required = true) String nome, Model model) {
        List<Pessoa> resultados = pessoaDao.buscarPorNome(nome);
        model.addAttribute("pessoas", resultados);
        model.addAttribute("pessoa", new Pessoa());
        return "listar_pessoas";
    }

    @RequestMapping("excluirPessoa")
    public String excluirPessoa(@RequestParam(value = "id", required = true) Long id, Model model) {
        pessoaDao.excluir(id);
        return listarPessoas(model);
    }

    @RequestMapping("editarPessoa")
    public String editarPessoa(@RequestParam(value = "id", required = true) Long id, Model model) {
        Pessoa pessoa = pessoaDao.buscarPorId(id);

        if (pessoa != null) {
            model.addAttribute("pessoa", pessoa);
        } else {
            model.addAttribute("pessoa", new Pessoa());
        }

        return "formulario_pessoa";
    }

}
