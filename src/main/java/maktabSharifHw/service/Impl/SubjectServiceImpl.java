package maktabSharifHw.service.Impl;

import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Subject;
import maktabSharifHw.repository.Impl.SubjectRepositoryImpl;
import maktabSharifHw.repository.SubjectRepository;
import maktabSharifHw.service.SubjectService;
import maktabSharifHw.util.EntityManagerProvider;

import javax.management.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements SubjectService {
    EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    SubjectRepository subjectRepository = new SubjectRepositoryImpl(entityManagerProvider);

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void printAllSubjects() throws GenerallyNotFoundException {
        List<Subject> subjectList = null;
        try {
            subjectList = subjectRepository.getAll();
        } catch (Exception e) {
            throw new GenerallyNotFoundException("subjects not founded");
        }
        for (Subject subject : subjectList) {
            System.out.println(subject);
        }
    }

    @Override
    public void printSubjectById(long id) throws GenerallyNotFoundException {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            System.out.println(subject.get());
        } else {
            throw new GenerallyNotFoundException("subject not founded");
        }
    }

    @Override
    public void deleteSubject(long id) throws GenerallyNotFoundException {
        try {
            subjectRepository.deleteBookBySubjectId(id);
            subjectRepository.delete(id);
        } catch (Exception e) {
            throw new GenerallyNotFoundException("subject not founded");
        }
    }

    @Override
    public void addSubject(String title, String description) {
        Subject subject = new Subject();
        subject.setCreate_time(new Date());
        subject.setSubjectTitle(title);
        subject.setSubjectDescription(description);
        subjectRepository.saveOrUpdate(subject);
    }

    @Override
    public void updateSubject(long id ,String title, String description) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setSubjectTitle(title);
        subject.setSubjectDescription(description);
        subjectRepository.saveOrUpdate(subject);
    }

    @Override
    public void showSubjectsWhoHaveOneBook() {
        List<Subject> subjectList = subjectRepository.ShowSubjectsWhoHaveOneBook();
        for (Subject subject : subjectList) {
            System.out.println(subject);
        }
    }
}
