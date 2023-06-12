package BusinessLogic.Mappers;

import Shared.Entities.Student;
import Shared.ViewModels.CreateStudentVm;

public class CreateStudentVmToStudent {
    public static Student map(CreateStudentVm createStudentVm) {
        return new Student(
                createStudentVm.getFirstName(),
                createStudentVm.getLastName(),
                createStudentVm.getStudentIndex(),
                null
        );
    }
}
