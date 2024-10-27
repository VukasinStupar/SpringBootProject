package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.GradeDto;
import rs.ac.uns.ftn.springsecurityexample.model.Grade;

public interface GradeService {

	Grade create(GradeDto dto, Long userId);

	List<Grade> findGradeUserId(Long guestId);

	Grade findGradeById(Long gradeId);

	Grade update(GradeDto dto);

}
