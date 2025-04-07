package maktabSharifHw.service;

import maktabSharifHw.exception.GenerallyNotFoundException;

public interface SubjectService {
    void printAllSubjects() throws GenerallyNotFoundException;
    void deleteSubject(long id) throws GenerallyNotFoundException;
    void addSubject(String title , String description);
    void updateSubject(long id,String title , String description);
   void showSubjectsWhoHaveOneBook();
}
