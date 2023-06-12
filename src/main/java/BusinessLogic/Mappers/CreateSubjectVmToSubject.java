package BusinessLogic.Mappers;

import Shared.Entities.Subject;
import Shared.ViewModels.CreateSubjectVm;

public class CreateSubjectVmToSubject {
    public static Subject map(CreateSubjectVm createSubjectVm) {
        return new Subject(
                createSubjectVm.getSubjectName()
        );
    }
}
