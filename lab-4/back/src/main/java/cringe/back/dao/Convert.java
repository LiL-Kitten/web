package cringe.back.dao;

public interface Convert<E, D> {
    E convertToEntity(D dto);

    D convertToDTO(E entity);
}
