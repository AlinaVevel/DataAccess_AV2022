package com.av2022.apirest.model.dao;

import com.av2022.apirest.model.entity.ReservationJPAEntity;
import org.springframework.data.repository.CrudRepository;

public interface IReservationEntityDAO extends CrudRepository<ReservationJPAEntity, Integer> {
}
