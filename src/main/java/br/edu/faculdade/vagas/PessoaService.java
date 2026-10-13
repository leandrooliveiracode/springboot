package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<PessoaResposta> listar() {
        return repositorio.todas().stream()
                .map(this::paraResposta)
                .toList();
    }

    public Optional<PessoaResposta> buscarPorId(String id) {
        return repositorio.porId(id)
                .map(this::paraResposta);
    }

    public PessoaResposta criar(PessoaEntrada entrada) {
        Pessoa nova = new Pessoa(
                UUID.randomUUID().toString(),
                entrada.nome(),
                entrada.email(),
                entrada.area(),
                entrada.senioridade()
        );

        Pessoa salva = repositorio.salvar(nova);
        return paraResposta(salva);
    }

    public Optional<PessoaResposta> trocar(
            String id, PessoaEntrada entrada) {

        Pessoa nova = new Pessoa(
                id,
                entrada.nome(),
                entrada.email(),
                entrada.area(),
                entrada.senioridade()
        );

        return repositorio.trocar(id, nova)
                .map(this::paraResposta);
    }

    public boolean apagar(String id) {
        return repositorio.remover(id);
    }

    private PessoaResposta paraResposta(Pessoa pessoa) {
        return new PessoaResposta(
                pessoa.id(),
                pessoa.nome(),
                pessoa.email(),
                pessoa.area(),
                pessoa.senioridade()
        );
    }
}
