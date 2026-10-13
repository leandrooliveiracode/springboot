
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

    public List<Pessoa> listar() {
        return repositorio.todas();
    }

    public Optional<Pessoa> buscarPorId(String id) {
        return repositorio.todas().stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }

    public Pessoa criar(Pessoa nova) {
        Pessoa comId = new Pessoa(
                UUID.randomUUID().toString(),
                nova.nome(),
                nova.email(),
                nova.area(),
                nova.senioridade()
        );

        return repositorio.salvar(comId);
    }

    public Optional<Pessoa> trocar(String id, Pessoa nova) {
        Pessoa comId = new Pessoa(
                id,
                nova.nome(),
                nova.email(),
                nova.area(),
                nova.senioridade()
        );

        return repositorio.trocar(id, comId);
    }

    public boolean apagar(String id) {
        return repositorio.remover(id);
    }
}
