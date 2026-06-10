package com.adoptme.api.registrodesaparecidos.service;

import com.adoptme.api.registrodesaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registrodesaparecidos.dto.AtualizarCoordenadasDTO;
import com.adoptme.api.registrodesaparecidos.dto.AtualizarStatusDTO;
import com.adoptme.api.registrodesaparecidos.dto.RegistroDesaparecidoResponseDTO;
import com.adoptme.api.registrodesaparecidos.enums.StatusDesaparecido;
import com.adoptme.api.registrodesaparecidos.repository.RegistroDesaparecidoRepository;
import com.adoptme.api.usuario.domain.Usuario;
import com.adoptme.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroDesaparecidoService {

    @Autowired
    private RegistroDesaparecidoRepository registroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrar novo registro de animal desaparecido
    public RegistroDesaparecidoResponseDTO cadastrar(RegistroDesaparecido dados, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuarioId));

        dados.setUsuario(usuario);
        RegistroDesaparecido salvo = registroRepository.save(dados);
        return new RegistroDesaparecidoResponseDTO(salvo);
    }

    // Listar todos os registros
    public List<RegistroDesaparecidoResponseDTO> listarTodos() {
        return registroRepository.findAll()
                .stream()
                .map(RegistroDesaparecidoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Listar filtrando por status (DESAPARECIDO ou ENCONTRADO)
    public List<RegistroDesaparecidoResponseDTO> listarPorStatus(StatusDesaparecido status) {
        return registroRepository.findByStatus(status)
                .stream()
                .map(RegistroDesaparecidoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Listar por usuário
    public List<RegistroDesaparecidoResponseDTO> listarPorUsuario(Long usuarioId) {
        return registroRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(RegistroDesaparecidoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<RegistroDesaparecidoResponseDTO> buscarPorId(Long id) {
        return registroRepository.findById(id)
                .map(RegistroDesaparecidoResponseDTO::new);
    }

    // PATCH — Alternância de status (DESAPARECIDO <-> ENCONTRADO)
    // Usa métodos do domínio para encapsular a transição
    public RegistroDesaparecidoResponseDTO atualizarStatus(Long id, AtualizarStatusDTO dto) {
        RegistroDesaparecido registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado com id: " + id));

        if (dto.getStatus() == StatusDesaparecido.ENCONTRADO) {
            registro.marcarComoEncontrado();
        } else {
            registro.marcarComoDesaparecido();
        }

        return new RegistroDesaparecidoResponseDTO(registroRepository.save(registro));
    }

    // PATCH — Atualizar coordenadas do último avistamento no mapa
    // Validação de range geográfico delegada ao domínio
    public RegistroDesaparecidoResponseDTO atualizarCoordenadas(Long id, AtualizarCoordenadasDTO dto) {
        RegistroDesaparecido registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado com id: " + id));

        registro.atualizarCoordenadas(dto.getUltimaLatitude(), dto.getUltimaLongitude());

        return new RegistroDesaparecidoResponseDTO(registroRepository.save(registro));
    }

    // Deletar registro
    public void deletar(Long id) {
        if (!registroRepository.existsById(id)) {
            throw new RuntimeException("Registro não encontrado com id: " + id);
        }
        registroRepository.deleteById(id);
    }
}
