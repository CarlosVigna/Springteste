package com.example.springteste.controllers;


import com.example.springteste.dtos.ContasRecordDto;
import com.example.springteste.models.ContasModel;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.ContasRepository;
import com.example.springteste.repositories.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContasController {

@Autowired
private ContasRepository contasRepository;

@Autowired
private UsuariosRepository usuariosRepository;

@PostMapping("/usuarios/{nome}")
public ResponseEntity<Object> saveConta (@PathVariable (value = "nome") String nome,
        @RequestBody @Valid ContasRecordDto contasRecordDto) {
    Optional<UsuariosModel> usuarioOpt = usuariosRepository.findByNome(nome);
    if (usuarioOpt.isEmpty()){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
    }

    UsuariosModel usuariosModel = usuarioOpt.get();
    var contasModel = new ContasModel();
    BeanUtils.copyProperties(contasRecordDto, contasModel);
    contasModel.addUsuario(usuariosModel);
    usuariosModel.getContas().add(contasModel);
    return ResponseEntity.status(HttpStatus.OK).body(contasRepository.save(contasModel))  ;

    }

}
