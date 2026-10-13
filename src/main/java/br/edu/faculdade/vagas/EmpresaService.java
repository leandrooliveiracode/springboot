
package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<EmpresaResposta> listar() {
        return repositorio.todas()
                .stream()
                .map(this::paraResposta)
                .toList();
    }

    public Optional<EmpresaResposta> buscarPorSlug(String slug) {
        return repositorio.porSlug(slug)
                .map(this::paraResposta);
    }

    public EmpresaResposta criar(EmpresaEntrada entrada) {

        if (repositorio.porSlug(entrada.slug()).isPresent()) {
            throw new EmpresaSlugDuplicadoException(entrada.slug());
        }

        Empresa nova = new Empresa(
                UUID.randomUUID().toString(),
                entrada.nome(),
                entrada.slug(),
                entrada.site(),
                entrada.descricao()
        );

        Empresa salva = repositorio.salvar(nova);

        return paraResposta(salva);
    }

    public boolean apagar(String slug) {
        return repositorio.remover(slug);
    }

    private EmpresaResposta paraResposta(Empresa empresa) {
        return new EmpresaResposta(
                empresa.id(),
                empresa.nome(),
                empresa.slug(),
                empresa.site(),
                empresa.descricao()
        );
    }
}
