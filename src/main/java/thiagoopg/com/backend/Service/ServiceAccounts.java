package thiagoopg.com.backend.Service;

import thiagoopg.com.backend.Dto.CreateContasDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Repository.RepositoryAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAccounts {

     final private RepositoryAccount contaRepository;

    public ServiceAccounts(RepositoryAccount contaRepository) {
        this.contaRepository = contaRepository;
    }

    //------------------------------------------------------------
    //Criar Conta
    public Integer createAccount(CreateContasDto createContasDto){
        var entity = new Account(createContasDto.name());
        var saved = contaRepository.save(entity);
        return saved.getId_account();
    }
    //Pegar todas as contas
    public List<Account> getAllAccounts(){
        return contaRepository.findAll();
    }
    public void updateNameOfAccount(Integer id, String nome){
        contaRepository.findById(id).get().setName(nome);
    }
    //Deletar todas as contas
    public boolean deleteAllAccount(){
        contaRepository.deleteAll();
        return true;
    }
}
