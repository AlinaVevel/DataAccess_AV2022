package com.av2022.apirest.model.dao;

import com.av2022.apirest.model.entity.BooksJPAEntityFinal;
import com.av2022.apirest.model.entity.ReservationJPAEntityFinal;
import org.springframework.data.repository.CrudRepository;

public interface IBookEntityDAO extends CrudRepository<BooksJPAEntityFinal, String> {

}
