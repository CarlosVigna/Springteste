package com.example.springteste.controllers;


import com.example.springteste.dtos.CategoriasRecordDto;
import com.example.springteste.dtos.UsuariosRecordDto;
import com.example.springteste.models.CategoriasModel;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.CategoriasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @PostMapping ("/categorias")
    public ResponseEntity<CategoriasModel> saveCategoria (@RequestBody @Valid CategoriasRecordDto categoriasRecordDto){
        var categoriasModel = new CategoriasModel();
        BeanUtils.copyProperties(categoriasRecordDto, categoriasModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoriasModel));
    }

}
