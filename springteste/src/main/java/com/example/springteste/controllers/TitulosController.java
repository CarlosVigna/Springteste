package com.example.springteste.controllers;


import com.example.springteste.dtos.TitulosRecordDto;
import com.example.springteste.models.CategoriasModel;
import com.example.springteste.models.TitulosModel;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.CategoriasRepository;
import com.example.springteste.repositories.TitulosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TitulosController {

    @Autowired
    private TitulosRepository titulosRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/titulos")
    public ResponseEntity<String> saveTitulo(@RequestBody @Valid TitulosRecordDto titulosRecordDto) {
        // Verificando se a categoria existe
        if (!categoriasRepository.existsById(titulosRecordDto.categoriaId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
        }

        // Criando um novo modelo de título
        var titulosModel = new TitulosModel();
        BeanUtils.copyProperties(titulosRecordDto, titulosModel);

        // Definindo a categoria manualmente
        CategoriasModel categoria = categoriasRepository.findById(titulosRecordDto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        titulosModel.setCategoria(categoria);

        // Salvando e retornando o título
        titulosRepository.save(titulosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Título salvo com sucesso: " + titulosModel.getId());
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/titulos")
    public ResponseEntity<List<TitulosModel>> getAllTitulo() {
        return ResponseEntity.status(HttpStatus.OK).body(titulosRepository.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/titulos/id")
    public ResponseEntity<Object> updateTitulo(@PathVariable(value = "id") Long id, @RequestBody @Valid TitulosRecordDto titulosRecordDto) {
        Optional<TitulosModel> titulos0 = titulosRepository.findById(id);
        if (titulos0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Título não encontrado");
        }
        var titulosModel = titulos0.get();
        BeanUtils.copyProperties(titulosRecordDto, titulosModel);
        return ResponseEntity.status(HttpStatus.OK).body(titulosRepository.save(titulosModel));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/titulos/{id}")
    public ResponseEntity<Object> deleteTitulo(@PathVariable(value = "id") Long id, @RequestBody @Valid TitulosRecordDto titulosRecordDto) {
        Optional<TitulosModel> titulos0 = titulosRepository.findById(id);
        if (titulos0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Titulo não encontrado");
        }
        titulosRepository.delete(titulos0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Titulo deletado com sucesso");
    }
}