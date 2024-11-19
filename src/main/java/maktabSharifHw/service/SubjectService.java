package maktabSharifHw.service;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Subject;

import java.util.List;

public interface SubjectService {
    void printAllSubjects() throws GenerallyNotFoundException;
    void printSubjectById(long id) throws GenerallyNotFoundException;
    void deleteSubject(long id) throws GenerallyNotFoundException;
    void addSubject(String title , String description);
    void updateSubject(long id,String title , String description);
   void showSubjectsWhoHaveOneBook();
}
