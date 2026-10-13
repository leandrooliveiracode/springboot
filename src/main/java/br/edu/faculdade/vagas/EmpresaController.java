
package br.edu.faculdade.vagas;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService servico;

    public EmpresaController(EmpresaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<EmpresaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{slug}")
    public ResponseEntity<EmpresaResposta> porSlug(
            @PathVariable String slug) {

        return servico.buscarPorSlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresaResposta> criar(
            @Valid @RequestBody EmpresaEntrada entrada) {

        EmpresaResposta salva = servico.criar(entrada);

        URI onde = URI.create("/empresas/" + salva.slug());

        return ResponseEntity
                .created(onde)
                .body(salva);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> apagar(
            @PathVariable String slug) {

        return servico.apagar(slug)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
