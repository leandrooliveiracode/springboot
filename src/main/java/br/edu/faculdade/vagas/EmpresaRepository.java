
package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpresaRepository {

    private final List<Empresa> empresas = new ArrayList<>(List.of(
            new Empresa(
                    "1", "Aurora Tech", "aurora-tech",
                    "https://aurora.tech",
                    "Produto de gestão para clínicas. Time pequeno, remoto desde 2020."
            ),
            new Empresa(
                    "2", "Nuvem Rosa", "nuvem-rosa",
                    "https://nuvemrosa.com.br",
                    "Consultoria de dados para o varejo do Nordeste."
            ),
            new Empresa(
                    "3", "Mareh Digital", "mareh-digital",
                    "https://mareh.digital",
                    "Agência de produtos digitais em Olinda."
            ),
            new Empresa(
                    "4", "Coral Labs", "coral-labs",
                    "https://corallabs.io",
                    "Plataforma de educação corporativa."
            ),
            new Empresa(
                    "5", "Tucano Systems", "tucano-systems",
                    "https://tucano.sys.br",
                    "Integrações e APIs para logística."
            )
    ));

    public synchronized List<Empresa> todas() {
        return List.copyOf(empresas);
    }

    public synchronized Optional<Empresa> porSlug(String slug) {
        return empresas.stream()
                .filter(empresa -> empresa.slug().equals(slug))
                .findFirst();
    }

    public synchronized Empresa salvar(Empresa nova) {
        empresas.add(nova);
        return nova;
    }

    public synchronized boolean remover(String slug) {
        return empresas.removeIf(
                empresa -> empresa.slug().equals(slug)
        );
    }
}
