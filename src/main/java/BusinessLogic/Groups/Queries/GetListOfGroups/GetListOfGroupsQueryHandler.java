package BusinessLogic.Groups.Queries.GetListOfGroups;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.StudentGroup;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania listy grup.
 * Implementuje interfejs IRequestHandler.
 */
public class GetListOfGroupsQueryHandler implements IRequestHandler {
    /**
     * Repozytorium grupy.
     */
    StudentGroupRepository _studentGroupRepository;
    /**
     * Konstruktor klasy GetListOfGroupsQueryHandler.
     * Tworzy obiekt klasy StudentGroupRepository.
     */
    public GetListOfGroupsQueryHandler() {
        _studentGroupRepository = new StudentGroupRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania listy grup.
     *
     * @param json DataHandler<String> w formacie JSON gdzie string może być pusty ponieważ nie ma wpływu na wynik.
     * @return ResponseHandler<ArrayList < StudentGroup>> w formacie JSON gdzie ArrayList<StudentGroup> jest listą grup.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać listy grup.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            ArrayList<StudentGroup> studentGroups = _studentGroupRepository.getListOfGroups();
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<StudentGroup>>(studentGroups, new ArrayList<>(), true));
        }
        catch (Exception e)
        {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<StudentGroup>>(null, errors, false));
        }
    }
}
