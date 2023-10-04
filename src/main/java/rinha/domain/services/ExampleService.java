package rinha.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rinha.domain.enumeration.ExceptionMessagesEnum;
import rinha.domain.exceptions.NotModifiedException;
import rinha.domain.entities.Example;
import rinha.domain.repositories.ExampleRepository;

@Service
@Transactional(readOnly = true)
public class ExampleService {

	@Autowired
	private ExampleRepository processRepository;

	public Optional<Example> getById(final Long processId) {
		return processRepository.findById(processId);
	}
	
	public Example updateStatus(final Example processVersionOld, final Boolean newStatus) {

		if (processVersionOld.isActive() != newStatus) {
			processVersionOld.setActive(newStatus);;
		} else {
			throw new NotModifiedException(ExceptionMessagesEnum.NOT_MODIFIED_DEFAULT);
		}

		return processRepository.save(processVersionOld);
	}
}