package org.example.plus.domain.log.service;

import lombok.RequiredArgsConstructor;
import org.example.plus.domain.log.entity.Log;
import org.example.plus.domain.log.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createLog(String url, String requestMethod, Long managerId){
        Log log = new Log(url, requestMethod, managerId);
        logRepository.save(log);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void successLog(String url, String requestMethod, Long managerId, String status){
        Log log = new Log(url, requestMethod, managerId, status);
        logRepository.save(log);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void failedLog(String url, String requestMethod, Long managerId, String errorMessage, String status){
        Log log = new Log(url, requestMethod, managerId, errorMessage, status);
        logRepository.save(log);
    }
}
