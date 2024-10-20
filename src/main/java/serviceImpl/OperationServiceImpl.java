package serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daoImpl.OperationDaoImpl;
import dto.OperationDto;
import lombok.RequiredArgsConstructor;
import model.Account;
import model.Operation;
import service.OperationService;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final OperationDaoImpl operationDaoImpl;

	@Override
	public void saveOperation(Operation operation) {
			operationDaoImpl.save(operation);
	}

	@Override
	public void updateOperation(Operation operation) {
		operationDaoImpl.update(operation);

	}

	@Override
	public void deleteOperation(Long id) {
		operationDaoImpl.delete(id);

	}

	@Override
	public Operation ListId(Long id) {
		return operationDaoImpl.findById(id);
	}

	@Override
	public List<Operation> operationsList() {
		return operationDaoImpl.findAll();
	}

	@Override
	public List<OperationDto> ListOperationAccount(Account account) {
		
		return operationDaoImpl.ListOperationAccount(account).stream().map( o -> new OperationDto(o.getType(), o.getValue(), o.getDate())).collect(Collectors.toList());
	}

}
