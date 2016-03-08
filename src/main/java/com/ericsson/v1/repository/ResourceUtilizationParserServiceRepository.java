package com.ericsson.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.v1.model.ResourceUtilizationBaseData;

//@Repository
public interface ResourceUtilizationParserServiceRepository extends CrudRepository<ResourceUtilizationBaseData, String> {

}
