package api.agenda_virtual.controller;

import api.agenda_virtual.model.Pessoa;
import api.agenda_virtual.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (!pessoaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoa.setId(id);
        Pessoa updatedPessoa = pessoaService.save(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        if (!pessoaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
