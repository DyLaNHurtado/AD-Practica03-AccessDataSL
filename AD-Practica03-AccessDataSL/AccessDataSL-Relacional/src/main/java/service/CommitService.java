package service;

import model.Commit;
import repository.RepoCommit;

import java.util.UUID;

public class CommitService extends BaseService<Commit, UUID, RepoCommit>{


    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CommitService(RepoCommit repository) {
        super(repository);
    }
}
