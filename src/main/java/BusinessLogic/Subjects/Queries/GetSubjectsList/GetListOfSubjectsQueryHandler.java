package BusinessLogic.Subjects.Queries.GetSubjectsList;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Subject;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania listy przedmiotów.
 * Implementuje interfejs IRequestHandler.
 */
public class GetListOfSubjectsQueryHandler implements IRequestHandler {
    /**
     * Repozytorium przedmiotu.
     */
    private final SubjectRepository _subjectRepository;

    /**
     * Konstruktor klasy GetListOfSubjectsQueryHandler.
     * Tworzy obiekt klasy SubjectRepository.
     */
    public GetListOfSubjectsQueryHandler() {
        _subjectRepository = new SubjectRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania listy przedmiotów.
     *
     * @param json DataHandler<String> w formacie JSON gdzie string może być pusty ponieważ nie ma wpływu na wynik.
     * @return ResponseHandler<ArrayList < Subject>> w formacie JSON gdzie ArrayList<Subject> jest listą przedmiotów.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać listy przedmiotów.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            ArrayList<Subject> subjects = _subjectRepository.getListOfSubjects();
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Subject>>(subjects, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Subject>>(null, e.getMessage(), false));
        }
    }
}
