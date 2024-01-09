package com.tickethub.app.service;
import com.tickethub.app.model.Solicitud;
import com.tickethub.app.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    // Obtener todas las solicitudes
    public List<Solicitud> findAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Obtener una solicitud por su ID
    public Optional<Solicitud> findSolicitudById(Long id) {
        return solicitudRepository.findById(id);
    }

    // Guardar o actualizar una solicitud
    public Solicitud saveOrUpdateSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // Eliminar una solicitud
    public void deleteSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }
}
