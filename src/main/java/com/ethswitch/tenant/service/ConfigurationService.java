package com.ethswitch.tenant.service;

import com.ethswitch.tenant.crud.exception.ResourceNotFoundException;
import com.ethswitch.tenant.domain.Configuration;
import com.ethswitch.tenant.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public Configuration createConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Optional<Configuration> getConfigurationById(Long id) {
        return configurationRepository.findById(id);
    }

    public Configuration updateConfiguration(Long id, Configuration configurationDetails) {
        Configuration configuration = configurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found for this id :: " + id));

        configuration.setKey(configurationDetails.getKey());
        configuration.setValue(configurationDetails.getValue());

        return configurationRepository.save(configuration);
    }

    public void deleteConfiguration(Long id) {
        Configuration configuration = configurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found for this id :: " + id));

        configurationRepository.delete(configuration);
    }
}
