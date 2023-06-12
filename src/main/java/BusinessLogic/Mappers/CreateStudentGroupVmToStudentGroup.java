package BusinessLogic.Mappers;

import Shared.Entities.StudentGroup;
import Shared.ViewModels.CreateStudentGroupVm;

public class CreateStudentGroupVmToStudentGroup {
    public static StudentGroup map(CreateStudentGroupVm createStudentGroupVm) {
        return new StudentGroup(
                createStudentGroupVm.getStudentGroupName()
        );
    }
}
