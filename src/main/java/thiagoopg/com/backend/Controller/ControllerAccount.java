package thiagoopg.com.backend.Controller;

import jakarta.persistence.PostUpdate;
import thiagoopg.com.backend.Dto.CreateContasDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Service.ServiceAccounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/")
public class ControllerAccount {

    final private ServiceAccounts serviceAccounts;

    public ControllerAccount(ServiceAccounts serviceAccounts) {
        this.serviceAccounts = serviceAccounts;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        var users = serviceAccounts.getAllAccounts();
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public Integer createAccount(@RequestBody CreateContasDto createContasDto) {
        return serviceAccounts.createAccount(createContasDto);
    }

    @PostUpdate
    public ResponseEntity<Void> updateNameOfAccount(@RequestBody Integer id, String nome) {
        serviceAccounts.updateNameOfAccount(id, nome);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public boolean deleteContas() {
        return serviceAccounts.deleteAllAccount();
    }
}
